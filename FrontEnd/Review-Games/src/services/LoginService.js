import axios from "axios";

export async function LoginService(email, password) {
  const loginBody = { email: email, password: password };

  try {
    const response = await axios.post(
      "http://localhost:8080/api/v1/auth/authenticate",
      loginBody
    );

    const { access_token, refresh_token } = response.data;
    localStorage.setItem("access_token", access_token);
    localStorage.setItem("refresh_token", refresh_token);

    return true; 
  } catch (error) {
    console.error("Couldn't log in", error);
    return false; 
  }
}
