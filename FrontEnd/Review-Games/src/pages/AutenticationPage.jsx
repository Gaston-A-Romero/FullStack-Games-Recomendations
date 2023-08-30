import { useEffect, useState } from 'react';
import { LoginService } from '../services/LoginService';
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
      try{        
        if(authToken !== null){
          setIsLogged(true);
        }
      }
      catch(error){
        console.error(error);
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
