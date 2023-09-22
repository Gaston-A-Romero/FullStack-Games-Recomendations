import { Outlet } from "react-router-dom";
import Header from "./components/Header";
import HomePage from "./pages/HomePage";



function App() {
  return (    
    <>
      <Header/>
      <Outlet/>
      
    </>
  );
}

export default App;
