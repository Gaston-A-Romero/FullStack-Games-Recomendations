import axios from "axios";
import { useEffect, useState } from "react";
import useGlobalState from "../store/store";
function Profile(){
    const [firstTimeLogged,setFirstTimeLogged] = useState(false);
    const [setProfile,profile,access_token] = useGlobalState((state) => [state.setProfile,state.profile,state.access_token])
    const fetchProfile = async () => {
        try{           
            const config = {headers:{Authorization: `Bearer ${access_token}`}};
            const response = await axios.get("http://localhost:8080/api/auth/profile/my-profile",config);
            const data = response.data;
            setProfile(data);

        }
        catch(error){
            console.error(error);
        }

    }
    const checkProfile = () => {
        if(profile.username === null){
            setFirstTimeLogged(true);
            
        }

    }
    useEffect(() => {
        fetchProfile();
        checkProfile()

    },[firstTimeLogged])
    return(
        firstTimeLogged ?
            <h1>h</h1>
            :
            <section className="profile">
                <article className="profile_presentation">
                    <div className="pics">
                        <img src={profile.profile_picture} alt="" className="profile_pic" />
                        <img src="" alt="" className="profile_banner" />
                        <h3 className="username">{profile.username}</h3>
                        <p className="prof_description">{profile.description}</p>
                        <div className="accounts">
                            <a href={profile.steam_account}></a>
                            <a href={profile.epic_account}></a>
                            <a href={profile.psn_account}></a>
                            <a href={profile.xbox_account}></a>
                            <a href={profile.nintendo_account}></a>
                        </div>
                    </div>
                </article>
                <article className="fav_games_container">
                        
                </article>
                <article className="prof_reviews_containter">

                </article>
            </section>

    )

    
}

export default Profile;