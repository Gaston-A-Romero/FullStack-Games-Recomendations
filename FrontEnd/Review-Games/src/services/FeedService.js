import axios from "axios";
export async function getFeed(access_token){
    const config = {headers:{Authorization: `Bearer ${access_token}`}};
    
    const response = await axios.get("http://localhost:8080/api/auth/profile/review/feed",config)
    .catch(function(error){
        alert(error.request.responseText)
    });    
    return response;
}