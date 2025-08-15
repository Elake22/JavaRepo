import { Link, NavLink } from "react-router-dom";

export default function NavBar(){
  return (
    <nav className="navbar navbar-expand-md bg-white border-bottom">
      <div className="container">
        <Link className="navbar-brand d-flex align-items-center gap-2" to="/">
          <span className="icon-circle"><i className="bi bi-wrench-adjustable"></i></span>
          <b>GaragePro</b>
        </Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div id="nav" className="collapse navbar-collapse">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item">
              <NavLink className="nav-link" to="/">Home</NavLink>
            </li>
            <li className="nav-item ms-md-2">
              <NavLink className="btn btn-accent " to="/book">Book an Appointment</NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
