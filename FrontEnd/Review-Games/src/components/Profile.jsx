import axios, { AxiosHeaders } from "axios";
import { useEffect, useState } from "react";
function Profile(){
    const [profile,setProfile] = useState({})
    const [firstTimeLogged,setFirstTimeLogged] = useState(true);
    const fetchProfile = async () => {
        try{
            const config = {headers:{Authorization:`Bearer ${localStorage.getItem('access_token')}`}};
            console.log(config);
            const response = await axios.get("http://localhost:8080/api/auth/profile/my-profile",config);
            const data = response.data;
            setProfile(data);
            console.log(data)

        }
        catch(error){
            console.error(error);
        }

    }
    const checkFirstTimeLogged = () => {
        if(profile.username === null){
            return <EditProfileModal />
        }

    }
    useEffect(() => {
        fetchProfile();
        checkFirstTimeLogged();

    },[])
    return(
        <div>
            <p>{profile.username}</p>
        </div>
    )

    
}

export default Profile;