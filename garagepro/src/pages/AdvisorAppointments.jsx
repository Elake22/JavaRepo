// src/pages/AdvisorAppointments.jsx
import { useEffect, useMemo, useState } from "react";
import { listBookings, patchBookingStatus } from "../lib/api.js";

const ALL_STATUSES = [
  "REQUESTED",
  "SCHEDULED",
  "CONFIRMED",
  "IN_PROGRESS",
  "COMPLETED",
  "CANCELED",
];

function fmtDate(iso) {
  if (!iso) return "—";
  const d = new Date(iso);
  return new Intl.DateTimeFormat(undefined, {
    weekday: "short",
    month: "short",
    day: "numeric",
    year: "numeric",
  }).format(d);
}
function slotFrom(iso) {
  if (!iso) return "—";
  const h = new Date(iso).getHours();
  return h < 12 ? "Morning" : "Afternoon";
}

function StatusBadge({ s }) {
  const map = {
    REQUESTED: "secondary",
    SCHEDULED: "secondary",
    CONFIRMED: "primary",
    IN_PROGRESS: "warning",
    COMPLETED: "success",
    CANCELED: "dark",
  };
  const cls = map[s] || "secondary";
  return <span className={`badge text-bg-${cls}`}>{s || "—"}</span>;
}

export default function AdvisorAppointments() {
  const [status, setStatus] = useState("");
  const [q, setQ] = useState("");
  const [rows, setRows] = useState([]);
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState("");
  const [savingId, setSavingId] = useState(null); // which row is saving

  async function fetchData() {
    try {
      setErr("");
      setLoading(true);
      const data = await listBookings({ status, q });
      setRows(Array.isArray(data) ? data : []);
    } catch (e) {
      setErr(e.message || "Failed to load bookings");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    fetchData();
  }, [status, q]);

  // Inline status change (optimistic)
  async function changeStatus(id, next) {
    setErr("");
    setSavingId(id);

    // optimistic update
    setRows((prev) => prev.map((r) => (r.id === id ? { ...r, status: next } : r)));
    try {
      const updated = await patchBookingStatus(id, next);
      // ensure UI matches server truth if it returns the booking
      if (updated && updated.status) {
        setRows((prev) => prev.map((r) => (r.id === id ? { ...r, status: updated.status } : r)));
      }
    } catch (e) {
      // revert by refetching (simple & correct), or keep a snapshot if you prefer
      await fetchData();
      setErr(e.message || "Failed to update status");
    } finally {
      setSavingId(null);
    }
  }

  const filtered = useMemo(() => {
    if (!q) return rows;
    const needle = q.toLowerCase();
    return rows.filter((b) => {
      const customer = `${b.customer?.firstName || ""} ${b.customer?.lastName || ""}`.toLowerCase();
      const email    = (b.customer?.email || "").toLowerCase();
      const phone    = (b.customer?.phone || "").toLowerCase();
      const vehicle  = (b.customer?.carModel || "").toLowerCase();
      const service  = (b.service?.name || "").toLowerCase();
      const slot     = (b.timePreference || slotFrom(b.appointmentAt)).toLowerCase();
      return [customer, email, phone, vehicle, service, slot, String(b.id)].some((x) => x.includes(needle));
    });
  }, [rows, q]);

  return (
    <div className="container py-4">
      <div className="d-flex align-items-center justify-content-between mb-3">
        <h1 className="h3 mb-0">Appointments</h1>
        <div className="d-flex gap-2">
          <select
            className="form-select"
            style={{ width: 220 }}
            value={status}
            onChange={(e) => setStatus(e.target.value)}
          >
            <option value="">All statuses</option>
            {ALL_STATUSES.map((s) => (
              <option key={s} value={s}>{s}</option>
            ))}
          </select>
          <input
            className="form-control"
            placeholder="Search name / email / phone / vehicle / slot"
            value={q}
            onChange={(e) => setQ(e.target.value)}
            style={{ width: 360 }}
          />
          <button className="btn btn-outline-secondary" onClick={fetchData}>
            Refresh
          </button>
        </div>
      </div>

      {err && <div className="alert alert-danger">{err}</div>}

      {loading ? (
        <div className="text-muted">Loading…</div>
      ) : filtered.length === 0 ? (
        <div className="alert alert-light border">No appointments found.</div>
      ) : (
        <div className="table-responsive">
          <table className="table align-middle">
            <thead className="table-light">
              <tr>
                <th>ID</th>
                <th>Customer</th>
                <th>Service</th>
                <th>Mechanic</th>
                <th>Date</th>
                <th>Time</th>
                <th>Quoted</th>
                <th style={{ minWidth: 200 }}>Status</th>
                <th>Notes</th>
              </tr>
            </thead>
            <tbody>
              {filtered.map((b) => {
                const customer = b.customer
                  ? `${b.customer.firstName || ""} ${b.customer.lastName || ""}`.trim()
                  : "—";
                const mechanic  = b.mechanic?.name || "—";
                const service   = b.service?.name || "—";
                const price     = Number(b.quotedPrice ?? 0).toFixed(2);
                const dateLabel = fmtDate(b.appointmentAt);
                const slotLabel = b.timePreference || slotFrom(b.appointmentAt);

                return (
                  <tr key={b.id}>
                    <td className="text-muted">#{b.id}</td>
                    <td>
                      <div className="fw-semibold">{customer || "—"}</div>
                      <div className="small text-muted">{b.customer?.email || "—"}</div>
                      <div className="small text-muted">{b.customer?.phone || "—"}</div>
                      {b.customer?.carModel && (
                        <div className="small text-muted">{b.customer.carModel}</div>
                      )}
                    </td>
                    <td>{service}</td>
                    <td>{mechanic}</td>
                    <td style={{ whiteSpace: "nowrap" }}>{dateLabel}</td>
                    <td>{slotLabel}</td>
                    <td>${price}</td>
                    <td>
                      <div className="d-flex align-items-center gap-2">
                        <select
                          className="form-select form-select-sm"
                          value={b.status || ""}
                          onChange={(e) => changeStatus(b.id, e.target.value)}
                          disabled={savingId === b.id}
                          style={{ maxWidth: 180 }}
                        >
                          {ALL_STATUSES.map((s) => (
                            <option key={s} value={s}>{s}</option>
                          ))}
                        </select>
                        <StatusBadge s={b.status} />
                        {savingId === b.id && (
                          <span className="small text-muted">Saving…</span>
                        )}
                      </div>
                    </td>
                    <td className="text-truncate" style={{ maxWidth: 280 }}>
                      {b.notes || "—"}
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
