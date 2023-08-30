export function useVeryficationToken(token){
    const expiration_access_token = localStorage.getItem("expiration_access_token");
    const expirationDate = new Date(expiration_access_token);
    const currentDate = new Date();
    if(expirationDate < currentDate){
        localStorage.clear();
        return false;
    }
    return true;

}
