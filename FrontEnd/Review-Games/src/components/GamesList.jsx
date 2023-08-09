import React,{ useState , useEffect } from "react";
import axios from 'axios'
import Game from './Game'
import Pagination from "./Pagination";




const GamesList = () => {
    const [games,setGames] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    const [currentPage,setCurrentPage] = useState(0);
    const [searchGame,setSearchGame] = useState('');
    const totalPages = 201;


    const getGames = async (page) => {
        const response = await axios.get(`http://localhost:8080/api/v1/games_page?page=${page}`);
        setGames(response.data);
        setIsLoading(false)
        
    }
    const handleInputGame = (e) => {
        e.preventDefault();
        setSearchGame(e.target.value);
        console.log(e.target.value);
    }
    const getGame = async () => {
        const response = await axios.get(`http://localhost:8080/api/v1/game?title=${searchGame}`);
        setGames(response.data);

    }
    
    useEffect(() => {        
        getGames(currentPage);       
        window.scrollTo(0,0);       

    },[currentPage])
    return (
        (isLoading ? 
        <h4>Loading...</h4> :
        
        <article>
            <h2>Games list</h2>
            <label htmlFor="search-game">Search a game: </label>
            <input type="text" id="search-game" value={searchGame} onChange={handleInputGame} />
            <button className="btn" onClick={getGame}>Go!</button>
            <Game key={games.id_game} games = {games}/>

            <Pagination 
                currentPage = {currentPage}
                totalPages = {totalPages}
                onPageChange = {setCurrentPage}
            />

        </article>)
        
    )
}

export default GamesList;