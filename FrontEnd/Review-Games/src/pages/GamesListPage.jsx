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

        <article className="w-full min-w-[350px] flex flex-col justify-center items-center bg-slate-200">
            <h2 className="text-lg p-1">Games list</h2> 
            <form onSubmit={handleSearchSubmit} className='p-2 m-2 '>
                    <input type="text" placeholder="Search Game..."
                     id="search-game" value={searchGame} onChange={handleSearchInput}
                     className="border-2 border-black p-2" />
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