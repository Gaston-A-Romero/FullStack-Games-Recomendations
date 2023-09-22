import{ useState , useEffect } from "react";
import Game from '../components/Game'
import Pagination from "../components/Pagination";
import Loading from "../components/Loading";
import { useGamesAPI } from "../hooks/UseGamesAPI";





function GamesListPage (){    
    const {isLoading, games, currentPage, setCurrentPage, totalPages, getGamesByPage, searchGameByTitle} = useGamesAPI();
    const [searchGame,setSearchGame] = useState('');

    const handleSearchInput = (e) => {
        e.preventDefault();
        setSearchGame(e.target.value);
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        searchGameByTitle(searchGame);         
        
    };
    useEffect(() => {       
        getGamesByPage(currentPage);
        window.scrollTo(0, 0);
        
    }, [currentPage]);

    return (
        (isLoading ? 

        <Loading/> :

        <article className="games-container">
            <h2 className="subtitle">Games list</h2> 
            <form onSubmit={handleSearchSubmit} className='form-search'>
                    <input type="text" id="search-game" value={searchGame} onChange={handleSearchInput} />
            </form> 
            
            
            <Game games={games}/>
            <Pagination 
                currentPage = {currentPage}
                totalPages = {totalPages}
                onPageChange={setCurrentPage}
            />

        </article>)
        
    )
}

export default GamesListPage;