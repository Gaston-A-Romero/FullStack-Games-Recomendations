import React, { useEffect, useState } from 'react';
import { Login } from '../services/LoginService';
import useGlobalState from '../store/store';
import { Navigate } from 'react-router-dom';

function AutenticationPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { isLogged, logInUser } = useGlobalState();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const { access_token, expiration_access_token } = await Login(email, password);
    logInUser(access_token, expiration_access_token);
  };


  if (isLogged) {
    return <Navigate to="/feed" replace={true} />;
  } else {
    return (
      <section className='flex justify-center items-center p-2 m-2'>
        <form onSubmit={handleSubmit} className='border border-gray-300 p-4 shadow-md'>
          <div className='flex justify-center items-center flex-col p-2 m-2'>
            <label htmlFor='email'>Email:</label>
            <input
              id='email'
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className='border-2 border-black p-2'
            />
            <label htmlFor='password'>Password:</label>
            <input
              id='password'
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className='border-2 border-black p-2'
            />
            <button type="submit" className='w-full bg-black text-white p-2 m-2 rounded-lg hover:bg-slate-400'>
              Log in
            </button>
          </div>
        </form>
      </section>
    );
  }
}

export default AutenticationPage;
