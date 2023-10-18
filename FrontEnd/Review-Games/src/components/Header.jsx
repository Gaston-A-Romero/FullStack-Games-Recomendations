import {Link, Outlet} from 'react-router-dom'
import useGlobalState from '../store/store';
import SearchBar from "./SearchBar"

function Header(){
    const state = useGlobalState();
    const { isLogged, logOut } = state;
    

  
    return (
        <>        
            <header className='flex justify-between items-center bg-black absoluite top-0 w-full'>
                <nav className='flex justify-between items-center w-full p-4 '>
                    <ul className='flex justify-between items-center gap-6 w-[80%]'>
                        <Link to="/" className=''>
                            <img src='.\src\assets\logo.png'
                            className='rounded w-24'/>
                        </Link>
                        <Link to="/games" className='text-white text-xl hover:underline hover:underline-offset-8'>Games</Link>
                        
                        {isLogged ?
                            <>
                                <Link to="/profile" className='text-white text-xl hover:underline hover:underline-offset-8'>Profile</Link>
                                <Link to="/feed" className='text-white text-xl hover:underline hover:underline-offset-8'>Feed</Link>
                                <button onClick={logOut}
                                className="text-white text-xl hover:underline hover:underline-offset-8">Log Out</button>
                            </>
                            :
                            <Link to="/auth" className='text-white text-xl hover:underline hover:underline-offset-8'>Social</Link>
                            
                        }
                        
                    </ul>
                </nav>
                <SearchBar/>
            </header>

            <Outlet/>

            <footer className=' relative bottom-0 w-full text-white bg-black flex justify-center items-center '>
                <p className=''>Created by Gastón Romero</p>
            </footer>
        </>

    )
}

export default Header;