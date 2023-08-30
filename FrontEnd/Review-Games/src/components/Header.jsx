import {Link} from 'react-router-dom'

function Header(){
    return (
        <header className='header'>
            <ul className='navbar'>
                <Link to="/" className='link'>Home</Link>
                <Link to="/games" className='link'>Games</Link>
                <Link to="/autentication" className='link'>Social</Link>
            </ul>
        </header>
    )
}

export default Header;