export default function ServiceCard({ svc, selected, onSelect }){
  const icons = {
    "Oil Change":"droplet",
    "Brake Inspection":"stoplights",
    "Tire Rotation":"arrow-repeat",
    "Engine Diagnostics":"cpu",
    "Battery Replacement":"battery-charging",
    "Transmission Service":"gear",
    "A/C Repair":"snow"
  };
  const icon = icons[svc.name] || "tools";
  return (
    <button
      type="button"
      onClick={() => onSelect(svc)}
      className={`service-card btn w-100 text-start p-3 ${selected ? "border-2 border-danger" : ""}`}
    >
      <div className="d-flex align-items-center gap-3">
        <span className="icon-circle"><i className={`bi bi-${icon}`}></i></span>
        <div className="flex-grow-1">
          <div className="fw-semibold">{svc.name}</div>
          <div className="text-muted small">{svc.description || "—"}</div>
        </div>
        <span className="badge rounded-pill badge-accent">${Number(svc.price).toFixed(2)}</span>
      </div>
    </button>
  );
}
