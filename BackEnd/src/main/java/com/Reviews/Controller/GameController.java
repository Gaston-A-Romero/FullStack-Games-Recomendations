package com.Reviews.Controller;
import com.Reviews.DTO.Game;
import com.Reviews.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@CrossOrigin("http://127.0.0.1:5173/")
@RestController
@RequestMapping("/api/v1/")
public class GameController {
    @Autowired
    private GameService gameService;
    @GetMapping("game")
    public ResponseEntity<List<Game>> getGame(@RequestParam String title) {
        List<Game> searched = gameService.searchGame(title);
        return ResponseEntity.ok(searched);
    }
    @GetMapping("games_page")
    public ResponseEntity<List<Game>> getAllGamesByPage(@RequestParam(defaultValue = "0") int page) {
        List<Game> gameList = gameService.getAllGames();
        List<Game> gamePage = gameService.gamesByPage(page,gameList);
        return ResponseEntity.ok(gamePage);
    }
    @GetMapping("game/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        Game game = gameService.findById(id);
        return ResponseEntity.ok(game);
    }
    @GetMapping("game/list")
    public ResponseEntity<List<Game>> getAllGames(){
        List<Game> allGames = gameService.getAllGames();
        return ResponseEntity.ok(allGames);
    }
    @GetMapping("games-by-year/{year_release}")
    public ResponseEntity<List<Game>> AllGamesByYear(@PathVariable Integer year_release,@RequestParam(defaultValue = "0") int page){
        List<Game> gamesYearRelease = gameService.gamesByYear_release(year_release);
        List<Game> gamePage = gameService.gamesByPage(page,gamesYearRelease);
        return ResponseEntity.ok(gamePage);
    }
    @GetMapping("/game_by_platform/{platform}")
    public ResponseEntity<List<Game>> getGamesByConsole(@PathVariable String platform,@RequestParam(defaultValue = "0") int page){
        List<Game> gameListPlatform = gameService.gamesByPlatform(platform);
        List<Game> gamePage = gameService.gamesByPage(page,gameListPlatform);
        return ResponseEntity.ok(gamePage);
    }

    //Both addGame and addScrapedData don't need to validate data because the users cant add a game or a list of games to the database
    @PostMapping("game")
    public ResponseEntity<String> addGame(@RequestBody Game game){
        gameService.addGame(game);
        return ResponseEntity.ok("Game added to database");
    }
    @PostMapping("game/scrapping")
    public String addScrapedData(@RequestBody List<Game> gameList){
        gameService.addScrappedGames(gameList);
        return "Games scraped added to db...";
    }

}
