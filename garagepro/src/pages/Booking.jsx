// src/pages/Booking.jsx
import { useEffect, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  getServices,
  getMechanics,
  createBooking,
  createCustomer,
} from "../lib/api";

// ---------------- helpers ----------------
const norm = (s) => (s || "").trim().toLowerCase();

function dedupeServicesByName(list) {
  const byName = new Map();
  for (const s of list || []) {
    const key = norm(s.name);
    if (!byName.has(key)) byName.set(key, s); // keep first occurrence
  }
  return [...byName.values()];
}

function dedupeMechanicsPreferSpecialty(list) {
  const byName = new Map();
  for (const m of list || []) {
    const key = norm(m.name);
    const prev = byName.get(key);
    // keep the one with a specialty if duplicates exist
    if (!prev || (!!m.specialty && !prev.specialty)) byName.set(key, m);
  }
  return [...byName.values()];
}

/* === Emoji icons to match Home ========================================= */
const EMOJI = {
  "oil change": "🛢️",
  "brake inspection": "🛞",
  "tire rotation": "🔁",
  "engine diagnostics": "🧰",
  "battery replacement": "🔋",
  "transmission service": "⚙️",
  "a/c repair": "❄️",
};
const emojiForService = (name = "") => {
  const key = name.trim().toLowerCase();
  if (key.includes("oil")) return EMOJI["oil change"];
  if (key.includes("brake")) return EMOJI["brake inspection"];
  if (key.includes("rotation") || key.includes("rotate")) return EMOJI["tire rotation"];
  if (key.includes("diagnostic") || key.includes("engine")) return EMOJI["engine diagnostics"];
  if (key.includes("battery")) return EMOJI["battery replacement"];
  if (key.includes("transmission") || key.includes("gear")) return EMOJI["transmission service"];
  if (/\ba\/c\b/.test(key) || /\bac\b/.test(key) || key.includes("air conditioning"))
    return EMOJI["a/c repair"];
  return "🛠️"; // default
};

// Local YYYY-MM-DD (avoid UTC issues)
function todayStr() {
  const d = new Date();
  const y = d.getFullYear();
  const m = String(d.getMonth() + 1).padStart(2, "0");
  const day = String(d.getDate()).padStart(2, "0");
  return `${y}-${m}-${day}`;
}
function toLocalDate(dateStr, timeStr) {
  if (!dateStr) return null;
  const [y, m, d] = dateStr.split("-").map(Number);
  const [hh, mm, ss] = (timeStr || "00:00:00").split(":").map(Number);
  return new Date(y, (m || 1) - 1, d || 1, hh || 0, mm || 0, ss || 0, 0);
}

// --- email validator helpers
const emailRe = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // simple name@domain.tld
const isValidEmail = (s) => emailRe.test((s || "").trim());

// -----------------------------------------

export default function Booking() {
  const nav = useNavigate();

  // data
  const [services, setServices] = useState([]);
  const [mechanics, setMechanics] = useState([]);

  // selections
  const [selectedServiceIds, setSelectedServiceIds] = useState([]);
  const [mechanicId, setMechanicId] = useState("");

  const today = useMemo(() => todayStr(), []);
  const [date, setDate] = useState(today);
  const [dateTouched, setDateTouched] = useState(false);
  const dateInvalid = dateTouched && date && date < today; // safe because YYYY-MM-DD

  const [period, setPeriod] = useState("MORNING"); // MORNING | AFTERNOON

  // customer (typed)
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName]   = useState("");
  const [email, setEmail]         = useState("");
  const [phone, setPhone]         = useState("");
  const [vehicle, setVehicle]     = useState("");

  const [phoneTouched, setPhoneTouched] = useState(false);
  const phoneDigits = (phone || "").replace(/\D/g, "");
  const phoneInvalid = phoneTouched && phoneDigits.length < 10;

  const [emailTouched, setEmailTouched] = useState(false);
  const emailInvalid = emailTouched && !isValidEmail(email);

  // misc
  const [notes, setNotes]   = useState("");
  const [error, setError]   = useState("");
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    (async () => {
      try {
        const [svc, mech] = await Promise.all([getServices(), getMechanics()]);
        setServices(dedupeServicesByName(svc || []));
        setMechanics(dedupeMechanicsPreferSpecialty(mech || []));
      } catch (e) {
        setError(e.message || "Failed to load data");
      }
    })();
  }, []);

  const total = useMemo(() => {
    const priceById = new Map(
      services.map((s) => [String(s.id), Number(s.price || 0)])
    );
    return selectedServiceIds.reduce(
      (sum, id) => sum + (priceById.get(String(id)) || 0),
      0
    );
  }, [selectedServiceIds, services]);

  function toggleService(id) {
    setSelectedServiceIds((prev) =>
      prev.includes(id) ? prev.filter((x) => x !== id) : [...prev, id]
    );
  }

  function periodTime() {
    return period === "MORNING" ? "09:00:00" : "14:00:00";
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");

    if (!firstName.trim() || !lastName.trim() || !email.trim()) {
      setError("Please enter customer name and email.");
      return;
    }
    if (!isValidEmail(email)) {
      setEmailTouched(true);
      setError("Please enter a valid email like name@example.com.");
      return;
    }
    if (!phone.trim() || phoneDigits.length < 10) {
      setPhoneTouched(true);
      setError("Please enter a valid 10-digit phone number.");
      return;
    }
    if (!mechanicId) {
      setError("Please select a mechanic.");
      return;
    }
    if (selectedServiceIds.length === 0) {
      setError("Please select at least one service.");
      return;
    }
    // Date must be today or future
    if (!date || date < today) {
      setDateTouched(true);
      setError("Please choose today or a future date.");
      return;
    }
    // And the chosen period must be in the future too
    const candidate = toLocalDate(
      date,
      period === "MORNING" ? "09:00:00" : "14:00:00"
    );
    if (!candidate || candidate.getTime() < Date.now()) {
      setDateTouched(true);
      setError("That time slot has already passed. Please pick a future slot or another day.");
      return;
    }

    setSubmitting(true);
    try {
      // 1) Create or update customer
      const newCustomer = await createCustomer({
        firstName: firstName.trim(),
        lastName:  lastName.trim(),
        email:     email.trim(),
        phone:     phone.trim(),
        carModel:  vehicle.trim(),
      });

      // 2) Build appointmentAt from date + morning/afternoon
      const appointmentAt = `${date}T${periodTime()}`;

      // 3) Primary service: first selected; rest go into notes
      const [primaryServiceId, ...rest] = selectedServiceIds;
      const restNames = services
        .filter((s) => rest.includes(s.id))
        .map((s) => s.name)
        .join(", ");
      const notesExtended =
        rest.length > 0
          ? `${notes || ""}${notes ? " | " : ""}Additional services: ${restNames}`
          : notes;

      // 4) Create booking with summed quote; force status REQUESTED
      const created = await createBooking({
        customer: { id: newCustomer.id },
        service:  { id: primaryServiceId },
        mechanic: { id: Number(mechanicId) },
        appointmentAt,
        quotedPrice: Number(total.toFixed(2)),
        status: "REQUESTED",
        notes: notesExtended,
      });

      // 5) Navigate to confirmation with booking + summary
      const allSelectedNames = services
        .filter((s) => selectedServiceIds.includes(s.id))
        .map((s) => s.name);
      const mechName =
        mechanics.find((m) => m.id === Number(mechanicId))?.name || "";

      const id = created?.id ?? "new";
      nav(`/confirm/${id}`, {
        state: {
          booking: created,
          summary: {
            customerName: `${firstName.trim()} ${lastName.trim()}`.trim(),
            serviceNames: allSelectedNames,
            mechanicName: mechName,
          },
        },
      });
    } catch (e) {
      console.error(e);
      setError(e.message || "Failed to create booking");
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <div className="container py-4">
      <h1 className="mb-3">Book an Appointment</h1>

      {error && <div className="alert alert-danger">{error}</div>}

      <form onSubmit={handleSubmit} noValidate>
        {/* Step 1: Contact Info + Vehicle */}
        <div className="card mb-4 shadow-sm">
          <div className="card-body">
            <h5 className="card-title mb-3">Contact Info</h5>
            <div className="row g-3">
              <div className="col-md-6">
                <label className="form-label">First Name</label>
                <input
                  className="form-control"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  required
                  placeholder="Lewis"
                  autoComplete="given-name"
                />
              </div>

              <div className="col-md-6">
                <label className="form-label">Last Name</label>
                <input
                  className="form-control"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  required
                  placeholder="Hamilton"
                  autoComplete="family-name"
                />
              </div>

              <div className="col-md-6">
                <label className="form-label">Email</label>
                <input
                  type="email"
                  className={`form-control ${emailInvalid ? "is-invalid" : ""}`}
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  onBlur={() => setEmailTouched(true)}
                  required
                  placeholder="name@example.com"
                  autoComplete="email"
                />
                {emailInvalid && (
                  <div className="invalid-feedback">
                    Please enter a valid email like <b>name@example.com</b>.
                  </div>
                )}
              </div>

              <div className="col-md-6">
                <label className="form-label">Phone</label>
                <input
                  type="tel"
                  inputMode="tel"
                  className={`form-control ${phoneInvalid ? "is-invalid" : ""}`}
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                  onBlur={() => setPhoneTouched(true)}
                  required
                  placeholder="(555) 123-4567"
                  autoComplete="tel"
                />
                {phoneInvalid && (
                  <div className="invalid-feedback">
                    Please enter a valid 10-digit phone number.
                  </div>
                )}
              </div>

              <div className="col-12">
                <label className="form-label">Vehicle (make / model)</label>
                <input
                  className="form-control"
                  placeholder="e.g., Ford Mustang GT"
                  value={vehicle}
                  onChange={(e) => setVehicle(e.target.value)}
                  autoComplete="off"
                />
              </div>
            </div>
          </div>
        </div>

        {/* Step 2: Services (multi-select, deduped) */}
        <div className="card mb-4 shadow-sm">
          <div className="card-body">
            <h5 className="card-title mb-3">Choose Services</h5>
            <div className="row row-cols-1 row-cols-sm-2 row-cols-lg-3 g-3">
              {services.map((s) => (
                <div className="col" key={s.id}>
                  <label
                    className={`service-card border rounded-3 p-3 h-100 d-flex gap-3 align-items-start ${
                      selectedServiceIds.includes(s.id)
                        ? "border-dark"
                        : "border-light"
                    }`}
                    htmlFor={`svc-${s.id}`}
                    style={{ cursor: "pointer" }}
                  >
                    <span className="icon-circle" aria-hidden="true">
                      {emojiForService(s.name)}
                    </span>
                    <div className="flex-grow-1">
                      <div className="d-flex justify-content-between align-items-center">
                        <span className="fw-semibold">{s.name}</span>
                        <span className="fw-semibold">
                          ${Number(s.price).toFixed(2)}
                        </span>
                      </div>
                      {s.description && (
                        <div className="text-muted small mt-1">
                          {s.description}
                        </div>
                      )}
                      <div className="form-check mt-2">
                        <input
                          className="form-check-input"
                          type="checkbox"
                          id={`svc-${s.id}`}
                          checked={selectedServiceIds.includes(s.id)}
                          onChange={() => toggleService(s.id)}
                        />
                        <label className="form-check-label" htmlFor={`svc-${s.id}`}>
                          Add to selection
                        </label>
                      </div>
                    </div>
                  </label>
                </div>
              ))}
            </div>

            <div className="d-flex justify-content-between align-items-center mt-3">
              <span className="text-muted">
                Select one or more services (icons shown for common services)
              </span>
              <span className="fs-5 fw-bold">Total: ${total.toFixed(2)}</span>
            </div>
          </div>
        </div>

        {/* Step 3: Mechanic (deduped, prefer specialty) */}
        <div className="card mb-4 shadow-sm">
          <div className="card-body">
            <h5 className="card-title mb-3">Pick a Mechanic</h5>
            <select
              className="form-select"
              value={mechanicId}
              onChange={(e) => setMechanicId(e.target.value)}
              required
            >
              <option value="" disabled>
                Choose a mechanic...
              </option>
              {mechanics.map((m) => (
                <option key={m.id} value={m.id}>
                  {m.name}
                  {m.specialty ? ` (${m.specialty})` : ""}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* Step 4: Date / Morning-Afternoon */}
        <div className="card mb-4 shadow-sm">
          <div className="card-body">
            <h5 className="card-title mb-3">Select Date & Time</h5>
            <div className="row g-3 align-items-end">
              <div className="col-md-6">
                <label className="form-label">Date</label>
                <input
                  type="date"
                  className={`form-control ${dateInvalid ? "is-invalid" : ""}`}
                  value={date}
                  min={today}
                  onChange={(e) => setDate(e.target.value)}
                  onBlur={() => setDateTouched(true)}
                  required
                />
                {dateInvalid && (
                  <div className="invalid-feedback">
                    Please choose today or a future date.
                  </div>
                )}
              </div>
              <div className="col-md-6">
                <label className="form-label me-3 d-block">Time of Day</label>
                <div className="btn-group" role="group">
                  <input
                    type="radio"
                    className="btn-check"
                    name="period"
                    id="p-am"
                    checked={period === "MORNING"}
                    onChange={() => setPeriod("MORNING")}
                  />
                  <label className="btn btn-outline-dark" htmlFor="p-am">
                    Morning
                  </label>

                  <input
                    type="radio"
                    className="btn-check"
                    name="period"
                    id="p-pm"
                    checked={period === "AFTERNOON"}
                    onChange={() => setPeriod("AFTERNOON")}
                  />
                  <label className="btn btn-outline-dark" htmlFor="p-pm">
                    Afternoon
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Notes only (status is forced to REQUESTED) */}
        <div className="mb-4">
          <label className="form-label">Notes (optional)</label>
          <textarea
            className="form-control"
            rows="3"
            value={notes}
            onChange={(e) => setNotes(e.target.value)}
          />
        </div>

        <div className="d-flex justify-content-between align-items-center">
          <div className="fs-5 fw-bold">Quoted Total: ${total.toFixed(2)}</div>
          <button
            type="submit"
            className="btn btn-dark"
            disabled={submitting || emailInvalid || phoneInvalid}
          >
            {submitting ? "Booking..." : "Book Appointment"}
          </button>
        </div>
      </form>
    </div>
  );
}
