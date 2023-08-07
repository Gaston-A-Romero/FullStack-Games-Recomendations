import React from 'react'
import { useState } from 'react';


const Autentication = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        // Aquí puedes realizar las operaciones de inicio de sesión, como enviar los datos al servidor
        console.log('Email:', email);
        console.log('Password:', password);
      };

    return(
        <div>
        <h2>Iniciar sesión</h2>
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
          <button type="submit">Iniciar sesión</button>
        </form>
      </div>
    )

}

export default Autentication;
