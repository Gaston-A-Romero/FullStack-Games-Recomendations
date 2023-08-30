import { useEffect, useState } from 'react';
import { LoginService } from '../services/LoginService';
import { useVeryficationToken } from '../services/useVeryficationToken';
import LoggedPage from './LoggedPage';


function AutenticationPage(){
    const authToken = localStorage.getItem('access_token');
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [isLogged, setIsLogged] = useState(false);

    const handleSubmit = async (event) => {
      event.preventDefault();
      const loggedIn = await LoginService(email, password);
      setIsLogged(loggedIn);
    };
    const refreshToken = async () => {
      const refreshToken = localStorage.getItem('refresh_token');
      const header = {headers:{Authorization:`Bearer ${refreshToken}`}};
      try{
        const response = await axios.post('http://localhost:8080/api/auth/refresh-token',header)
        const { access_token, refresh_token , expiration_access_token } = response.data;
        localStorage.setItem("access_token", access_token);
        localStorage.setItem("refresh_token", refresh_token);
        localStorage.setItem("expiration_access_token", expiration_access_token);

      }
      catch(error){
        console.error(error);
      }
    }

    useEffect(() => {
      if(authToken !== null){
        setIsLogged(useVeryficationToken(authToken)); 

        if(setIsLogged === false){
          refreshToken()
        }      
      }
      
      
    },[])

    return(
      !isLogged ? 
        <section className='autentication-container'>
          <form onSubmit={handleSubmit} className='login-form'>
            <div>
              <label htmlFor='email'>Email:</label>
              <input id='email'
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            <div>
              <label htmlFor='password'>Password:</label>
              <input id='password'
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
            <button type="submit">Log in</button>
          </form>
        </section>
    :
      <LoggedPage />
    )

}

export default AutenticationPage;
