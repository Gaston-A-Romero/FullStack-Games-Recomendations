import axios from "axios";
import useGlobalState from "../store/store";

export async function Login(email, password, logInUser) {
  const loginBody = { email: email, password: password };

  try {
    const response = await axios.post(
      "http://localhost:8080/api/v1/auth/authenticate",
      loginBody
    );
    const { access_token, refresh_token, expiration_access_token } = response.data;
    window.localStorage.setItem("refresh_token",refresh_token);
    return {access_token:access_token,expiration_access_token:expiration_access_token,error:false}
    

  } catch (error) {
    throw new Error("Error while trying to authenticate user")
  }
}
export async function LogOut(access_token){
  try{
    const config = { headers: { Authorization: `Bearer ${access_token}` } };
    await axios.post('http://localhost:8080/api/v1/auth/logout',config);

  }
  catch(error){
    throw new Error('Couldnt log out');
  }
}
export async function Register(email,password){
  try{
    const response = await axios.post('http://localhost:8080/api/v1/auth/register',{email,password})
    const {access_token,expiration_access_token,refresh_token} = response.data;
    window.localStorage.setItem("refresh_token",refresh_token);
    
  }
  catch(error){
    throw new Error('Couldnt perform registration');
  }
}
