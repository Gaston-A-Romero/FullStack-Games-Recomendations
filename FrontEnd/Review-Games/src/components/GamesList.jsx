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
    const [totalPages,setTotalPages] = useState(null);


    const getGamesByPage = async (page) => {
        const response = await axios.get(`http://localhost:8080/api/v1/games_page?page=${page}`)
        console.log(response);
        if(response.status !== 200){
            return new Error(response.message);
        }
        setGames(response.data.gamesList);
        setCurrentPage(page);
        setTotalPages(response.data.totalPages);
        setIsLoading(false);
    }
    const handleSearchInput = (e) => {
        e.preventDefault();
        setSearchGame(e.target.value)
    }
    const getGame = async (searchGame) => {
        console.log(searchGame);    
        const response = await axios.get(`http://localhost:8080/api/v1/game?title=${searchGame}`);
        setGames(response.data);
        

    }
    
    useEffect(() => {   
        if(searchGame === ""){
            getGamesByPage(currentPage);       
            window.scrollTo(0,0); 
        }     
        if(searchGame.length > 3){
            setTimeout(() => {
                getGame(searchGame);
                setCurrentPage(0);
                setTotalPages(0);

            },1000);
            
        }
             

    },[currentPage,searchGame])

    return (
        (isLoading ? 

        <Loading/> :

        <article className="games-container">
            <h2 className="subtitle">Games list</h2>         

                <form onSubmit={getGame}>
                    <input type="text" id="search-game" value={searchGame} onChange={handleSearchInput} />
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