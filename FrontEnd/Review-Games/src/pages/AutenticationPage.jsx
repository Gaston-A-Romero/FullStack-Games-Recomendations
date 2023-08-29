import { useState } from 'react';
import { LoginService } from '../services/LoginService';
import LoggedPage from './LoggedPage';


function AutenticationPage(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [isLogged, setIsLogged] = useState(false);

    const handleSubmit = async (event) => {
      event.preventDefault();
      const loggedIn = await LoginService(email, password);
      setIsLogged(loggedIn);
    };
    return(
      !isLogged ? 
        <section className='autentication-container'>
          <article className='buttons-form'>
            <button  className='btn'><h2>Log in</h2></button>
            <button className='btn'><h2>Register</h2></button>
          </article>
          <form onSubmit={handleSubmit} className='login-form'>
            <div>
              <label>Email:</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            <div>
              <label>Password:</label>
              <input
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
