// src/components/MechanicCard.jsx
import { Link } from "react-router-dom";

const norm = (s) => (s || "").trim().toLowerCase();

// Local photo overrides by mechanic name (files in /public/img/mechanics/)
const PHOTO = {
  "john smith":    "/img/mechanics/john-smith.jpg",
  "sarah johnson": "/img/mechanics/sarah-johnson.jpg",
  "mike rodriguez":"/img/mechanics/mike-rodriguez.jpg",
  "david wilson":  "/img/mechanics/david-wilson.jpg",
  "laura chen":    "/img/mechanics/laura-chen.jpg",
};

// Optional per-mechanic ratings (will be used if API doesn't send m.rating)
const RATINGS = {
  "john smith": 4.9,
  "sarah johnson": 4.5,
  "mike rodriguez": 4.6,
  "david wilson": 4.7,
  "laura chen": 4.9,
};

export default function MechanicCard({ m }) {
  const yrs = Number(m.yearsExperience || 0);
  const yrsLabel = yrs > 0 ? `${yrs} yrs` : "New";
  const first = (m.name || "").split(" ")[0] || "Mechanic";
  const key = norm(m.name);

  // Prefer a local photo by name, then API photoUrl, then a cute fallback
  const dicebear = `https://api.dicebear.com/8.x/adventurer/svg?seed=${encodeURIComponent(
    m.name || "mechanic"
  )}`;
  const initialSrc = PHOTO[key] || m.photoUrl || dicebear;

  const handleImgError = (e) => {
    // prevent loops, swap to fallback avatar
    if (e.currentTarget.src !== dicebear) {
      e.currentTarget.onerror = null;
      e.currentTarget.src = dicebear;
    }
  };

  // Rating: API value wins; else from table; else default 4.8
  const rating = Number(m.rating ?? RATINGS[key] ?? 4.8).toFixed(1);

  return (
    <div className="card mech-card h-100 shadow-sm">
      <div className="card-body d-flex align-items-center gap-3">
        <img
          src={initialSrc}
          alt={m.name}
          className="rounded-circle flex-shrink-0 mechanic-avatar"
          width="56"
          height="56"
          loading="lazy"
          decoding="async"
          onError={handleImgError}
          style={{ objectFit: "cover" }}
        />

        <div className="flex-grow-1">
          <div className="d-flex align-items-center justify-content-between">
            <h6 className="mb-0">{m.name}</h6>
            <span className="text-warning small">⭐ {rating}</span>
          </div>

          <div className="text-muted small mt-1">
            {m.specialty ? (
              <>
                <span className="badge badge-accent me-2 text-dark">
                  {m.specialty}
                </span>
                <span>• {yrsLabel}</span>
              </>
            ) : (
              <span>{yrsLabel}</span>
            )}
          </div>

          <div className="mt-3">
            <Link to={`/book?mechanic=${m.id}`} className="btn btn-sm btn-dark">
              Book with {first}
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
