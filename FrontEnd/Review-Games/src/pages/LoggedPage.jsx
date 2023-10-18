import AcountActivationPage from "./AcountActivationPage";
import Profile from "../components/Profile";
import useGlobalState from "../store/store";

const LoggedPage = () => {
    const isLogged = useGlobalState();
    
    return(
        isLogged ? <Profile/> : <AcountActivationPage />
    )

}
export default LoggedPage;