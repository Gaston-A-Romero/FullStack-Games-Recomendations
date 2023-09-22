import axios, { AxiosHeaders } from "axios";
import { useEffect, useState } from "react";
import useGlobalState from "../store/store";
function Profile(){
    const [firstTimeLogged,setFirstTimeLogged] = useState(true);
    const setProfile = useGlobalState((state) => state.setProfile)
    const fetchProfile = async () => {
        try{
            const config = {headers:{Authorization:`Bearer ${localStorage.getItem('access_token')}`}};
            const response = await axios.get("http://localhost:8080/api/auth/profile/my-profile",config);
            const data = response.data;
            setProfile(data);
            console.log(data) // -> data del perfil

        }
        catch(error){
            console.error(error);
        }

    }
    useEffect(() => {
        fetchProfile();

    },[])
    return(
        <div>
            
        </div>
    )

    
}

export default Profile;