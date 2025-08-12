// src/pages/Home.jsx
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getServices, getMechanics } from "../lib/api";
import MechanicCard from "../components/MechanicCard";

// --- helpers ------------------------------------------------------------
function dedupeServices(list) {
  // keep one row per service name; prefer the lowest price
  const map = new Map();
  for (const s of list || []) {
    const key = (s.name || "").trim().toLowerCase();
    const cur = map.get(key);
    if (!cur) map.set(key, s);
    else {
      const curPrice = Number(cur.price ?? Infinity);
      const newPrice = Number(s.price ?? Infinity);
      if (newPrice < curPrice) map.set(key, s);
    }
  }
  return [...map.values()];
}

function dedupeMechanics(list) {

  const map = new Map();
  for (const m of list || []) {
    const key = (m.name || "").trim().toLowerCase();
    const cur = map.get(key);
    if (!cur) map.set(key, m);
    else {
      const score =
        (m.specialty ? 1 : 0) - (cur.specialty ? 1 : 0) ||
        (Number(m.yearsExperience || 0) - Number(cur.yearsExperience || 0));
      if (score > 0) map.set(key, m);
    }
  }
  return [...map.values()];
}

const ICONS = {
  "oil change": "🛢️",
  "brake inspection": "🛑",
  "tire rotation": "🛞",
  "engine diagnostics": "🧪",
  "battery replacement": "🔋",
  "transmission service": "⚙️",
  "a/c repair": "❄️",
};
const DESCRIPTIONS = {
  "oil change": "Regular oil changes to keep your engine running smoothly.",
  "brake inspection": "Comprehensive brake system check for optimal safety.",
  "tire rotation": "Even out tire wear and extend tire lifespan.",
  "engine diagnostics": "Computerized diagnostics to identify engine issues.",
  "battery replacement": "Swap your battery and test electrical health.",
  "transmission service": "Keep your transmission shifting smoothly.",
  "a/c repair": "Stay cool with our air conditioning service.",
};
function iconFor(name) {
  const k = (name || "").toLowerCase();
  return ICONS[k] || "🧰";
}
function descFor(name, fallback) {
  const k = (name || "").toLowerCase();
  return DESCRIPTIONS[k] || fallback || "Quality service from certified techs.";
}

// --- component ----------------------------------------------------------
export default function Home() {
  const [services, setServices] = useState([]);
  const [mechanics, setMechanics] = useState([]);
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState("");

  useEffect(() => {
    (async () => {
      try {
        const [svc, mech] = await Promise.all([getServices(), getMechanics()]);
        setServices(dedupeServices(svc || []));
        setMechanics(dedupeMechanics(mech || []));
      } catch (e) {
        setErr(e.message || "Failed to load content");
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  return (
    <>
      {/* HERO */}
      <header className="hero">
        <div className="container">
          <div className="row align-items-center g-4">
            <div className="col-lg-7">
              <h1 className="display-5 fw-bold mb-3">
                Your <span style={{ color: "var(--red)" }}>Performance</span>, Our Priority
              </h1>
              <p className="lead text-muted mb-4">
                Book maintenance and repairs in minutes. No login. No fuss.
              </p>
              <div className="d-flex gap-3">
                <Link to="/book" className="btn btn-accent btn-lg">
                  Book an Appointment
                </Link>
                <a href="#services" className="btn btn-outline-dark btn-lg">
                  Explore Services
                </a>
              </div>
            </div>
            <div className="col-lg-5">
              <div className="border rounded-4 p-4 bg-white shadow-sm">
                <div className="d-flex align-items-center gap-3 mb-3">
                  <div className="icon-circle">🏁</div>
                  <div>
                    <div className="fw-semibold">Professional</div>
                    <div className="text-muted small">World Class Service</div>
                  </div>
                </div>
                <div className="d-flex align-items-center gap-3 mb-3">
                  <div className="icon-circle">⏱️</div>
                  <div>
                    <div className="fw-semibold">Fast Scheduling</div>
                    <div className="text-muted small">Pick morning or afternoon</div>
                  </div>
                </div>
                <div className="d-flex align-items-center gap-3">
                  <div className="icon-circle">🧰</div>
                  <div>
                    <div className="fw-semibold">Top Mechanics</div>
                    <div className="text-muted small">Trusted local experts</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>

      {/* SERVICES PREVIEW */}
      <section id="services" className="container py-5">
        <div className="d-flex align-items-center justify-content-between mb-3">
          <h2 className="mb-0">Popular Services</h2>
          <Link to="/book" className="btn btn-outline-dark">
            Book Now
          </Link>
        </div>

        {err && <div className="alert alert-danger">{err}</div>}
        {loading ? (
          <div className="text-muted">Loading services…</div>
        ) : (
          <div className="row g-3">
            {services.slice(0, 6).map((s) => (
              <div className="col-12 col-sm-6 col-lg-4" key={s.id}>
                <div className="card service-card h-100 shadow-sm">
                  <div className="card-body">
                    <div className="d-flex align-items-center gap-2 mb-2">
                      <div className="icon-circle">{iconFor(s.name)}</div>
                      <h6 className="mb-0">{s.name}</h6>
                    </div>
                    <p className="text-muted small mb-3">
                      {descFor(s.name, s.description)}
                    </p>
                    <div className="d-flex align-items-center justify-content-between">
                      <span className="fw-bold">${Number(s.price).toFixed(2)}</span>
                      <Link
                        to={`/book?service=${encodeURIComponent(s.id)}`}
                        className="btn btn-sm btn-accent"
                      >
                        Select
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
            ))}
            {!services.length && (
              <div className="col-12">
                <div className="alert alert-light border">No services found.</div>
              </div>
            )}
          </div>
        )}
      </section>

      {/* MECHANICS */}
      <section className="container pb-5">
        <h2 className="mb-3">Meet Our Mechanics</h2>
        {loading ? (
          <div className="text-muted">Loading mechanics…</div>
        ) : (
          <div className="row g-3">
            {mechanics.slice(0, 6).map((m) => (
              <div key={m.id} className="col-12 col-md-6 col-lg-4">
                <MechanicCard m={m} />
              </div>
            ))}
            {!mechanics.length && (
              <div className="col-12">
                <div className="alert alert-light border">No mechanics found.</div>
              </div>
            )}
          </div>
        )}
      </section>

      {/* CTA STRIP */}
      <section className="py-5 border-top">
        <div className="container d-flex flex-column flex-md-row align-items-center justify-content-between gap-3">
          <div>
            <h3 className="mb-1">Ready to roll?</h3>
            <div className="text-muted">
              Book now, payment coming soon.
            </div>
          </div>
          <Link to="/book" className="btn btn-accent btn-lg">
            Book an Appointment
          </Link>
        </div>
      </section>
    </>
  );
}
