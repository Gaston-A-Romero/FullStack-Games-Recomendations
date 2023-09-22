import{ useState} from "react";
import axios from 'axios'
import useGlobalState from "../store/store";

export function useGamesAPI(){
    const [games,setGames] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    const [currentPage,setCurrentPage] = useState(0);
    const [totalPages,setTotalPages] = useState(null);

    const getGamesByPage = async (page) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/v1/games_page?page=${page}`);
            setGames(response.data.gamesList);
            setCurrentPage(page);
            setTotalPages(response.data.totalPages);
            setIsLoading(false);

        } catch (error) {
            console.error("Error getting games:", error);
        }
    };

    const searchGameByTitle = async (title) => {
        
        try {
            const encodedTitle = encodeURIComponent(title);
            const response = await axios.get(`http://localhost:8080/api/v1/game?title=${encodedTitle}`);
            setGames(response.data);
            setCurrentPage(0);
            setTotalPages(0);
        } catch (error) {
            console.error("Error searching game:", error);
        }
    };

    return {
        games,
        isLoading,
        currentPage,
        totalPages,
        getGamesByPage,
        setCurrentPage,
        searchGameByTitle,
    };

}