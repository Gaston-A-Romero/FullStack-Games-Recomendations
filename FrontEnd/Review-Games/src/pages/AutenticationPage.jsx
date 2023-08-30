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
    

    useEffect(() => {
      if (authToken !== null) {
          const verifyToken = async () => {
              const verification = await useVeryficationToken();  
              setIsLogged(verification);   
          };
          
      verifyToken();
      }
    },[]);

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
