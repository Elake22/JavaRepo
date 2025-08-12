// src/pages/Confirm.jsx
import { useEffect, useState } from "react";
import { useLocation, useParams, Link } from "react-router-dom";
import { getBooking } from "../lib/api";

export default function Confirm() {
  const { id } = useParams();
  const { state } = useLocation();

  const [booking, setBooking] = useState(state?.booking || null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(!state?.booking);

  useEffect(() => {
    if (booking) return;
    (async () => {
      try {
        setLoading(true);
        const b = await getBooking(id);
        setBooking(b);
      } catch (e) {
        setError(e?.message || "Failed to load booking");
      } finally {
        setLoading(false);
      }
    })();
  }, [id, booking]);

  if (loading) {
    return (
      <div className="container py-5">
        <div className="text-muted">Loading booking…</div>
      </div>
    );
  }
  if (error) {
    return (
      <div className="container py-5">
        <div className="alert alert-danger">{error}</div>
        <Link to="/" className="btn btn-outline-dark mt-2">Back to Home</Link>
      </div>
    );
  }
  if (!booking) {
    return (
      <div className="container py-5">
        <div className="alert alert-warning">Booking not found.</div>
        <Link to="/" className="btn btn-outline-dark mt-2">Back to Home</Link>
      </div>
    );
  }

  // ---------- Formatting & fallbacks ----------
  const summary = state?.summary || {}; // what we optionally passed from Booking.jsx

  // Date + Morning/Afternoon label (no clock)
  const appt = new Date(booking.appointmentAt);
  const dateStr = appt.toLocaleDateString(undefined, { dateStyle: "full" });
  const period  = appt.getHours() < 12 ? "Morning" : "Afternoon";

  // Additional services encoded in notes
  const extraServices =
    (booking.notes || "").match(/Additional services:\s*(.+)$/i)?.[1] || "";

  // Prefer service from API; otherwise use the names array we passed in state
  const serviceSummary = booking.service?.name
    ? [booking.service.name, extraServices].filter(Boolean).join(", ")
    : (Array.isArray(summary.serviceNames) && summary.serviceNames.length
        ? [summary.serviceNames.join(", "), extraServices].filter(Boolean).join(", ")
        : extraServices || "—");

  // Customer details (prefer API, fall back to summary.customerName for display)
  const c = booking.customer || {};
  const customerName =
    c.firstName || c.lastName
      ? `${c.firstName ?? ""} ${c.lastName ?? ""}`.trim()
      : (summary.customerName || "—");
  const customerEmail = c.email || "";
  const customerPhone = c.phone || "";
  const customerVehicle = c.carModel || c.vehicle || "";

  const mechanicName = booking.mechanic?.name || summary.mechanicName || "—";
  const price = Number(booking.quotedPrice ?? 0).toFixed(2);

  return (
    <div className="container py-5">
      {/* Always green per your spec */}
      <h1 className="mb-2 text-success">Request received! 🎉</h1>
      <p className="lead">
        We’ve received your request and will confirm your appointment soon.
      </p>

      <div className="card shadow-sm my-4">
        <div className="card-body">
          <h5 className="card-title">Booking Summary</h5>
          <ul className="list-unstyled mb-0">
            <li><strong>ID:</strong> #{booking.id}</li>
            <li><strong>Customer:</strong> {customerName}</li>
            {customerEmail && <li><strong>Email:</strong> {customerEmail}</li>}
            {customerPhone && <li><strong>Phone:</strong> {customerPhone}</li>}
            {customerVehicle && <li><strong>Vehicle:</strong> {customerVehicle}</li>}
            <li><strong>Service(s):</strong> {serviceSummary}</li>
            <li><strong>Mechanic:</strong> {mechanicName}</li>
            <li><strong>When:</strong> {dateStr} — {period}</li>
            <li><strong>Quoted:</strong> ${price}</li>
            <li><strong>Status:</strong> {booking.status}</li>
            {booking.notes && <li><strong>Notes:</strong> {booking.notes}</li>}
          </ul>
        </div>
      </div>

      <div className="alert alert-warning">
        <p className="mb-1"><strong>Payment coming soon! 💳</strong></p>
        <p className="mb-0">A Service Advisor will call to confirm appointment time.</p>
      </div>

      <div className="d-flex gap-2">
        <Link to="/" className="btn btn-outline-dark">Back to Home</Link>
        <Link to="/book" className="btn btn-dark">Make Another Booking</Link>
      </div>
    </div>
  );
}
