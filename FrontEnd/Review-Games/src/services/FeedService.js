import axios from "axios";
export async function getFeed(access_token){
    const config = {headers:{Authorization: `Bearer ${access_token}`}};
    
        const response = await axios.get("http://localhost:8080/api/auth/profile/review/feed",config)
        .catch(function(error){
            console.log(error.toJSON())
        });
        console.log(response);
        const {feed} = response.data;

    
    
    
}