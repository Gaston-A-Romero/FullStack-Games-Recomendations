package com.Reviews.Services;
import com.Reviews.DTO.Game;
import com.Reviews.DTO.Genre;
import com.Reviews.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@Transactional
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GenreService genreService;
    public void saveGame(Game game){
        gameRepository.save(game);
    }
    public List<Game> searchGame(String gameName) {
        List<Game> game_searched = gameRepository.findByTitleContainingIgnoreCase(gameName);
        if (game_searched.isEmpty()){
            throw new NullPointerException("Game with title: "+gameName+" not found");
        }
        return game_searched;
    }

    public List<Game> getAllGames() {
        List<Game> gameList = gameRepository.findAll();
        return gameList;
    }

    public Game findById(Long idGame) {
        Optional<Game> game = gameRepository.findById(idGame);
        if (game.isEmpty()){
            throw new NullPointerException("Game with id: " + idGame + " not found");
        }
        return game.get();
    }
    public List<Game> gamesByPage(int page,List<Game> gameList) {
        if (gameList.isEmpty()){
            throw new RuntimeException("The List of Games is Empty");
        }
        int pageSize = 50; // number of games per page
        //prevents input of a negative value
        int absolutePage = Math.abs(page);

        int fromIndex = absolutePage * pageSize;
        //Control function for not getting a list out of range
        if (fromIndex >= gameList.size()) {
            fromIndex = gameList.size() - 1;
        }
        int toIndex = Math.min(fromIndex + pageSize, gameList.size());
        List<Game> gamesPage = gameList.subList(fromIndex, toIndex);
        return gamesPage;
    }

    public List<Game> gamesByYear_release(Integer year_release) {
        return gameRepository.findByYear(year_release);
    }

    public List<Game> gamesByPlatform(String platform) {
        List<String> consoles = Arrays.asList("Xbox Series X", "Xbox One", "Xbox 360", "Xbox", "Wii U", "Wii", "Switch", "Stadia", "PSP", "PlayStation Vita"
                , "PlayStation 5", "PlayStation 4", "PlayStation 3", "PlayStation 2", "PlayStation", "PC", "Nintendo 64", "GameCube", "Game Boy Advance", "DS",
                "Dreamcast", "3DS");
        if (!consoles.contains(platform)){
            throw new RuntimeException("Platform: " + platform + " not found");
        }
        List<Game> games = gameRepository.findByPlatform(platform);
        if (games.isEmpty()){
            throw new RuntimeException("No games for that platform where found");
        }
        return games;
    }

    public void addGame(Game game) {
        Set<Genre> all_game_genres = new HashSet<>();
        Game new_game = new Game();
        for (Genre game_genre: game.getGenres()){
            Genre gen = genreService.searchByName(game_genre.getGenre_name());
            //if the genre doesn't exist it will create a new one
            if (gen == null){
                Genre new_genre = new Genre(game_genre.getGenre_name());
                all_game_genres.add(new_genre);
                genreService.saveGenre(new_genre);
            }
            // if the genre is found we just add the genre to the set of genres from this particular game
            else{
                all_game_genres.add(gen);
            }
        }
        new_game.setTitle(game.getTitle());
        new_game.setPlatform(game.getPlatform());
        new_game.setCompany(game.getCompany());
        new_game.setRelease_date(game.getRelease_date());
        new_game.setYear(game.getYear());
        new_game.setDeveloper(game.getDeveloper());
        new_game.setGenres(all_game_genres);
        new_game.setPicture(game.getPicture());
        gameRepository.save(new_game);
    }

    public void addScrappedGames(List<Game> gameList) {
        for (Game game:gameList) {
            this.addGame(game);
        }
    }
}
