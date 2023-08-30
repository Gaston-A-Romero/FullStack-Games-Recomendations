import axios from "axios";

export async function useVeryficationToken(setIsLogged){
    const refreshToken = async () => {
        const header = {
            headers: {
              Authorization: `Bearer ${localStorage.getItem('refresh_token')}`
            }
          };
        try{
          const response = await axios.post('http://localhost:8080/api/v1/auth/refresh-token',null,header)
          const { access_token, refresh_token , expiration_access_token } = response.data;
        

          localStorage.setItem("access_token", access_token);
          localStorage.setItem("refresh_token", refresh_token);
          localStorage.setItem("expiration_access_token", expiration_access_token);
          console.log("Token actualizado");
          return true;
  
        }
        catch(error){
          localStorage.clear();
          return false;
        }
      }

    const expiration_access_token = localStorage.getItem("expiration_access_token");
    
    // returns miliseconds
    const expirationDate = new Date(expiration_access_token).getTime();
    const currentDate = new Date().getTime();

    const expirationTimeLeft = expirationDate - currentDate;

    if (0 < expirationTimeLeft && expirationTimeLeft < 1000 * 60 * 5) {
        console.log("refreshing token auto");
        return await refreshToken();
    }
    else if (expirationTimeLeft <= 0){
        localStorage.clear()
        return false
    }   
    return true;
    

}
