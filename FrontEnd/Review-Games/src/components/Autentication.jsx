import { useState } from 'react';

const Autentication = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log('Email:', email);
        console.log('Password:', password);
      };

    const LogInForm = () => {
      return

    } 
    const RegisterForm = () =>{

    }

    return(
        <section className='autentication-container'>
          <article className='buttons-form'>
            <button onClick={LogInForm} className='btn'><h2>Log in</h2></button>
            <button onClick={RegisterForm}className='btn'><h2>Register</h2></button>
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
    )

}

export default Autentication;
