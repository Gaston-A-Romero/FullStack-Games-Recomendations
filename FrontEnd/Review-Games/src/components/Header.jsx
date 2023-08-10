
import {Link} from 'react-router-dom'

const Header = () => {
    return (
        <header className='header'>
            <nav className='navbar'>
                <Link to="/" className='link'>Home</Link>
                <Link to="/games" className='link'>Games</Link>
                <Link to="/autentication" className='link'>Autentication</Link>
            </nav>
        </header>
    )
}

export default Header;