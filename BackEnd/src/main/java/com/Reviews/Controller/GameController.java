package com.Reviews.Controller;
import com.Reviews.DTO.Game;
import com.Reviews.DTO.Genre;
import com.Reviews.Services.GameService;
import com.Reviews.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;



@RestController()
@RequestMapping("/api/v1/")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private GenreService genreService;
    @GetMapping("game")
    public ResponseEntity<Game> getGame(@RequestParam String title) throws Exception {
        Game searched = gameService.searchGame(title);
        if (searched == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(searched);
        }

    }
    @GetMapping("games_page")
    public List<Game> getGamesByPage(@RequestParam(defaultValue = "0") int page) {
        int pageSize = 50; // número de juegos por página
        //prevents input of a negative value
        int absolutePage = Math.abs(page);
        List<Game> gameList = gameService.getAllGames();
        int fromIndex = absolutePage * pageSize;
        if (fromIndex >= gameList.size()) {
            fromIndex = gameList.size() - 1;
        }
        int toIndex = Math.min(fromIndex + pageSize, gameList.size());
        List<Game> gamesPage = gameList.subList(fromIndex, toIndex);
        return gamesPage;
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
    @GetMapping("games-by-year/{year_release}")
    public List<Game> AllGamesByYear(@PathVariable Integer year_release) throws RuntimeException {
        List<Game> games_by_year = new ArrayList<>();
        LocalDate now = LocalDate.now();
        Integer year = now.getYear();
        if(year_release > year || year_release < 1995 && year_release != 0){
            throw new RuntimeException("Games from year: "+ year_release + " couldn't be found");
        }
        else {
            List<Game> games_year = gameService.getAllGames();
            for (Game game:games_year) {
                if(game.getYear_release().equals(year_release)){
                    games_by_year.add(game);
                }
            }
        }
        return games_by_year;
    }
    @GetMapping("/game_by_platform/{platform}")
    public List<Game> getGamesByConsole(@PathVariable String platform){
        List<Game> games_by_platform = new ArrayList<>();
        List<String> consoles = Arrays.asList("Xbox Series X", "Xbox One", "Xbox 360", "Xbox", "Wii U", "Wii", "Switch", "Stadia", "PSP", "PlayStation Vita"
                , "PlayStation 5", "PlayStation 4", "PlayStation 3", "PlayStation 2", "PlayStation", "PC", "Nintendo 64", "GameCube", "Game Boy Advance", "DS",
                "Dreamcast", "3DS");
        if (!consoles.contains(platform)){
            throw new RuntimeException("Platform: "+platform+" not found");
        }
        else {
            List<Game> games = gameService.getAllGames();
            for (Game game:games) {
                if(game.getPlatform().equals(platform)){
                    games_by_platform.add(game);
                }
            }
        }
        return games_by_platform;
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
        for (Game game:gameList) {
            this.addGame(game);
        }
        return "Games scraped added to db...";

    }




}
