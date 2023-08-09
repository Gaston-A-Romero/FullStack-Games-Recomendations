import React from 'react'
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
        <div>
        <button onClick={LogInForm}><h2>Log in</h2></button>
        <button onClick={RegisterForm}><h2>Register</h2></button>
        <form onSubmit={handleSubmit}>
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

      </div>
    )

}

export default Autentication;
