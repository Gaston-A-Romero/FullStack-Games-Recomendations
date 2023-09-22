import axios from "axios";

export async function LoginService(email, password, logInUser) {
  const loginBody = { email: email, password: password };

  try {
    const response = await axios.post(
      "http://localhost:8080/api/v1/auth/authenticate",
      loginBody
    );
    const { access_token, refresh_token, expiration_access_token } = response.data;
    window.localStorage.setItem("refresh_token",refresh_token);
    return {access_token:access_token,expiration_access_token:expiration_access_token}
    

  } catch (error) {
    throw new Error("Error while trying to authenticate user")
  }
}
