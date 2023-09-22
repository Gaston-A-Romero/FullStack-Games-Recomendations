import { Navigate} from "react-router-dom";
import useGlobalState from "../store/store";

export  function ProtectedRoute({children}){
    const logged = useGlobalState((state) => state.isLogged);
    if(!logged){
        return <Navigate to="/auth" replace={true} />;
    }
    return children;
    
}