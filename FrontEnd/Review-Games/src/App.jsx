import React from "react";
import { BrowserRouter as Router, Route, Link, Routes, Outlet } from "react-router-dom";
import GamesList from "./components/GamesList";
import Autentication from "./components/Autentication";
import HomePage from "./pages/HomePage";

function App() {
  return (
    <Router>
      <header>
        <nav>
          <Link to="/home">Home</Link>
          <Link to="/games">Games</Link>
          <Link to="/autentication">Autentication</Link>
        </nav>
      </header>
      <Routes>
        <Route path="/home" element={<HomePage />} />
        <Route path="/games" element={<GamesList />} />
        <Route path="/autentication" element={<Autentication />} />
      </Routes>
    </Router>
  );
}

export default App;
