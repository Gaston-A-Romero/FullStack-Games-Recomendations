import React,{ useState , useEffect } from "react";
import axios from 'axios'
import Game from '../components/Game'
import Pagination from "./Pagination";



const GamesList = () => {
    const [games,setGames] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(0);

    const totalPages = 201;



    const getGames = async () => {
        const response = await axios.get('http://localhost:8080/api/v1/games_page');
        setGames(response.data);
        setIsLoading(false)
        
    }
    
    useEffect(() => {        
        getGames();

    },[])
    return (
        (isLoading ? 
        <h4>Loading...</h4> :
        
        <article>
            <h2>Games list</h2>
            <label htmlFor="search-game">Search a game: </label>
            <input type="text" id="search-game" />
            <Game key={games.id_game} games = {games}/>

            <Pagination/>

        </article>)
        
    )
}

export default GamesList;