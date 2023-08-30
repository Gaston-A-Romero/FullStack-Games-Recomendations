import { useEffect, useState } from "react";
import AcountActivationPage from "./AcountActivationPage";
import Profile from "../components/Profile";
import Feed from "../components/Feed";
const LoggedPage = () => {
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