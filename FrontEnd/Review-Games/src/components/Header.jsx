import {Link} from 'react-router-dom'
import { useEffect, useState } from 'react';
import { useGamesAPI } from '../hooks/UseGamesAPI';
import useGlobalState from '../store/store';

function Header(){
    const state = useGlobalState();
    const { isLogged, logOut } = state;
    

  
    return (
        <header className='header'>
            <nav className='navbar'>
                <ul className='navbar-list'>
                    <Link to="/home" className='link'>LOGO</Link>
                    <Link to="/games" className='link'>Games</Link>
                    
                    {isLogged ?
                        <>
                            <Link to="/auth/profile" className='link'>Profile</Link>
                            <Link to="/auth/feed" className='link'>Feed</Link>
                            <button onClick={logOut}>Log Out</button>
                        </>
                        :
                        <Link to="/auth" className='link'>Social</Link>
                        
                    }
                    
                </ul>
            </nav>
        </header>
        
    )
}

export default Header;