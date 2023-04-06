package com.Reviews.Services;

import com.Reviews.DTO.Game;
import com.Reviews.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    public void saveGame(Game game){
        gameRepository.save(game);
    }


    public Game searchGame(String gameName) {
        List<Game> all_games = gameRepository.findAll();
        Game searched_game = null;
        for (Game game: all_games
             ) {
            if (game.getTitle().equals(gameName)){
                searched_game = game;
                break;
            }
            else{
                searched_game = null;
            }
        }
        return searched_game;
    }

    public List<Game> getAllGames() {
        List<Game> gameList = gameRepository.findAll();
        return gameList;
    }

    public Optional<Game> findById(Long idGame) {
        Optional<Game> game = gameRepository.findById(idGame);
        return game;
    }
}
