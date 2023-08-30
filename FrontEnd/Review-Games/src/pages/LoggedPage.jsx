import { useEffect, useState } from "react";
import { BrowserRouter as Router , Routes , Route } from "react-router-dom";
import AcountActivationPage from "./AcountActivationPage";
import Profile from "../components/Profile";
import Feed from "../components/Feed";
const LoggedPage = () => {
    const authToken = localStorage.getItem('access_token');
    const [isAccountEnabled,setisAccountEnabled] = useState(true);
    const checkAccountActivated = () => {
        return true
    }
    useEffect(() => {
        checkAccountActivated()
    },[])
    return(
        isAccountEnabled ? 
        <>
            <Profile/>
            <Feed/>
        </>
        : <AcountActivationPage />
    )

}
export default LoggedPage;