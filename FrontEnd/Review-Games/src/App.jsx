import React from "react";
import { BrowserRouter as Router, Route, Link, Routes, Outlet } from "react-router-dom";
import GamesList from "./components/GamesList";
import Autentication from "./components/Autentication";
import HomePage from "./pages/HomePage";
import Header from "./components/Header";

function App() {
  return (
    
    <Router>
      <Header/>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/games" element={<GamesList />} />
        <Route path="/autentication" element={<Autentication />} />
      </Routes>
    </Router>
  );
}

export default App;
