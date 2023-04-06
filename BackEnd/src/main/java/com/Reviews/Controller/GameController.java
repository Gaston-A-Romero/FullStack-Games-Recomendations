package com.Reviews.Controller;
import com.Reviews.DTO.Game;
import com.Reviews.DTO.Genre;
import com.Reviews.Services.GameService;
import com.Reviews.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController()
@RequestMapping("/api/v1/")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private GenreService genreService;
    @GetMapping("game")
    public ResponseEntity<Game> getGame(@RequestParam String title){
        Game searched = gameService.searchGame(title);
        if (searched == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(searched);
        }

    }
    @GetMapping("game/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        Optional<Game> gameOptional = gameService.findById(id);
        return gameOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("game/list")
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }
    @PostMapping("game")
    public ResponseEntity<?> addGame(@RequestBody Game game){
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
            // if the genre is found we just add the gen to the set of genres from this particular game
            else{
                all_game_genres.add(gen);
            }
        }
        new_game.setTitle(game.getTitle());
        new_game.setPlatform(game.getPlatform());
        new_game.setCompany(game.getCompany());
        new_game.setRelease_date(game.getRelease_date());
        new_game.setYear_release(game.getYear_release());
        new_game.setDeveloper(game.getDeveloper());
        new_game.setGenres(all_game_genres);
        new_game.setPicture(game.getPicture());

        gameService.saveGame(new_game);

        return new ResponseEntity<>("Game added...",HttpStatus.OK);
    }
    @PostMapping("game/scrapping")
    public String addScrapedData(@RequestBody List<Game> gameList){
        for (Game game:gameList
             ) {
            this.addGame(game);
        }
        return "Games scraped added to db...";

    }




}
