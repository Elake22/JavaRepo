import { Routes, Route } from "react-router-dom";
import NavBar from "/src/components/NavBar.jsx";
import Home from "./pages/Home.jsx";
import Booking from "./pages/Booking.jsx";
import Confirm from "./pages/Confirm.jsx";

export default function App(){
  return (
    <>
      <NavBar />
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/book" element={<Booking/>} />
        <Route path="/confirm/:id" element={<Confirm />} />
      </Routes>
      <footer className="py-4 border-top mt-5">
        <div className="container d-flex justify-content-between">
          <span className="footer-note">© {new Date().getFullYear()} GaragePro</span>
          <span className="footer-note">No login required • Payment coming soon</span>
        </div>
      </footer>
    </>
  );
}
