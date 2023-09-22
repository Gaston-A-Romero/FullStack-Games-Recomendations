import {useState } from 'react';
import { LoginService } from '../services/LoginService';
import LoggedPage from './LoggedPage';
import useGlobalState from '../store/store'

function AutenticationPage(){
    const authToken = localStorage.getItem('access_token');
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const {isLogged,logInUser} = useGlobalState();

    const handleSubmit = async (event) => {
      event.preventDefault();
      const { access_token , expiration_access_token } = await LoginService(email, password);
      logInUser(access_token,expiration_access_token);
    };
    

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
