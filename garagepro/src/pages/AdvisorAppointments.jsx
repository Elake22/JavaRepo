// src/pages/AdvisorAppointments.jsx
import { useEffect, useMemo, useState } from "react";
import { listBookings } from "../lib/api.js";

function fmtWhen(iso) {
  if (!iso) return "—";
  const d = new Date(iso);
  const label = d.getHours() < 12 ? "Morning" : "Afternoon";
  const date = new Intl.DateTimeFormat(undefined, {
    weekday: "short",
    month: "short",
    day: "numeric",
    year: "numeric",
  }).format(d);
  return `${date} • ${label}`;
}

function StatusBadge({ s }) {
  const map = {
    REQUESTED: "secondary",
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

  const filtered = useMemo(() => {
    if (!q) return rows;
    const needle = q.toLowerCase();
    return rows.filter((b) => {
      const customer = `${b.customer?.firstName || ""} ${b.customer?.lastName || ""}`.toLowerCase();
      const email = (b.customer?.email || "").toLowerCase();
      const phone = (b.customer?.phone || "").toLowerCase();
      const vehicle = (b.customer?.carModel || "").toLowerCase(); // <- vehicle included in search
      const service = (b.service?.name || "").toLowerCase();
      return [customer, email, phone, vehicle, service, String(b.id)].some((x) => x.includes(needle));
    });
  }, [rows, q]);

  return (
    <div className="container py-4">
      <div className="d-flex align-items-center justify-content-between mb-3">
        <h1 className="h3 mb-0">Appointments</h1>
        <div className="d-flex gap-2">
          <select
            className="form-select"
            style={{ width: 200 }}
            value={status}
            onChange={(e) => setStatus(e.target.value)}
          >
            <option value="">All statuses</option>
            <option>REQUESTED</option>
            <option>CONFIRMED</option>
            <option>IN PROGRESS</option>
            <option>COMPLETED</option>
            <option>CANCELED</option>
          </select>
          <input
            className="form-control"
            placeholder="Search name / email / phone / vehicle"
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
                <th>When</th>
                <th>Quoted</th>
                <th>Status</th>
                <th>Notes</th>
              </tr>
            </thead>
            <tbody>
              {filtered.map((b) => {
                const customer = b.customer
                  ? `${b.customer.firstName || ""} ${b.customer.lastName || ""}`.trim()
                  : "—";
                const mechanic = b.mechanic?.name || "—";
                const service = b.service?.name || "—";
                const price = Number(b.quotedPrice ?? 0).toFixed(2);
                const when = fmtWhen(b.appointmentAt);

                return (
                  <tr key={b.id}>
                    <td className="text-muted">#{b.id}</td>
                    <td>
                      <div className="fw-semibold">{customer || "—"}</div>
                      <div className="small text-muted">{b.customer?.email || "—"}</div>
                      <div className="small text-muted">{b.customer?.phone || "—"}</div>
                      {/* NEW: vehicle displayed */}
                      {b.customer?.carModel && (
                        <div className="small text-muted">{b.customer.carModel}</div>
                      )}
                    </td>
                    <td>{service}</td>
                    <td>{mechanic}</td>
                    <td>{when}</td>
                    <td>${price}</td>
                    <td>
                      <StatusBadge s={b.status} />
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
