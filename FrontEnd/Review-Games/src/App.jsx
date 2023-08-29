import { BrowserRouter as Router, Route, Link, Routes, Outlet } from "react-router-dom";
import HomePage from "./pages/HomePage";
import Header from "./components/Header";
import AutenticationPage from "./pages/AutenticationPage";
import GamesListPage from "./pages/GamesListPage";


function App() {
  return (    
    <Router>
      <Header/>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/games" element={<GamesListPage />} />
        <Route path="/autentication" element={<AutenticationPage />} />
      </Routes>
    </Router>
  );
}

export default App;
