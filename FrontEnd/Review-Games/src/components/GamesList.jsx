import{ useState , useEffect } from "react";
import axios from 'axios'
import Game from './Game'
import Pagination from "./Pagination";
import Loading from "./Loading";




function GamesList (){
    const [games,setGames] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    const [currentPage,setCurrentPage] = useState(0);
    const [searchGame,setSearchGame] = useState('');
    const totalPages = 201;


    const getGamesByPage = async (page) => {
        const response = await axios.get(`http://localhost:8080/api/v1/games_page?page=${page}`);
        setGames(response.data);
        setIsLoading(false)
    }
    const handleSearchInput = (e) => {
        e.preventDefault();
        setSearchGame(e.target.value)
    }
    const getGame = async () => {
        console.log(searchGame);    
        const response = await axios.get(`http://localhost:8080/api/v1/game?title=${searchGame}`);
        setGames(response.data);
        

    }
    
    useEffect(() => {        
        getGamesByPage(currentPage);       
        window.scrollTo(0,0);       

    },[currentPage])

    return (
        (isLoading ? 

        <Loading/> :

        <article className="games-container">
            <h2 className="subtitle">Games list</h2>         

            <form className="form-search" onSubmit={getGame}>
                <input type="text" id="search-game" value={searchGame} onChange={handleSearchInput} />
                <button className="btn" onClick={getGame} >Go!</button>
            </form>             
            <Game games={games}/>
            <Pagination 
                currentPage = {currentPage}
                totalPages = {totalPages}
                onPageChange = {setCurrentPage}
            />

        </article>)
        
    )
}

export default GamesList;