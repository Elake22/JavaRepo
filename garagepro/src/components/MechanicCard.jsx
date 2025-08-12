// src/components/MechanicCard.jsx
import { Link } from "react-router-dom";

export default function MechanicCard({ m }) {
  const yrs = Number(m.yearsExperience || 0);
  const yrsLabel = yrs > 0 ? `${yrs} yrs` : "New";
  const firstName = (m.name || "").split(" ")[0] || "Mechanic";

  // avatar
  const avatar =
    m.photoUrl ||
    `https://api.dicebear.com/8.x/adventurer/svg?seed=${encodeURIComponent(
      m.name || "mechanic"
    )}`;

  // simple placeholder rating 
  const rating = (m.rating ?? 4.8).toFixed(1);

  return (
    <div className="card mech-card h-100 shadow-sm">
      <div className="card-body d-flex align-items-center gap-3">
        <img
          src={avatar}
          alt={m.name}
          className="rounded-circle flex-shrink-0 mech-avatar"
          width="56"
          height="56"
        />
        <div className="flex-grow-1">
          <div className="d-flex align-items-center justify-content-between">
            <h6 className="mb-0">{m.name}</h6>
            <span className="text-warning small">⭐ {rating}</span>
          </div>

          <div className="text-muted small mt-1">
            {m.specialty ? (
              <>
                <span className="badge bg-accent me-2">{m.specialty}</span>
                <span>• {yrsLabel}</span>
              </>
            ) : (
              <span>{yrsLabel}</span>
            )}
          </div>

          <div className="mt-3">
            <Link
              to={`/book?mechanic=${m.id}`}
              className="btn btn-sm btn-dark"
            >
              Book with {firstName}
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
