import React from "react";
import { useState,useEffect } from "react";
import axios from 'axios'
import Game from '../components/Game'



const GamesList = () => {
    const {games,setGames} = useState([]);
    const {isLoading,setIsLoading} = useState(false);

    useEffect(() => {
        const getGames = async () => {
            const response = axios.get('A');
            const data = response.json();
            setGames(data);
            console.log(data);
        }
        getGames();
    },[])
    return (
        (!isLoading ? 
        <h4>Loading...</h4> :
        
        <article>
            <Game {...games}/>
        </article>)
        
    )
}

export default GamesList;