package com.Reviews.Controller;

import com.Reviews.DTO.Game;
import com.Reviews.DTO.Genre;
import com.Reviews.Services.GameService;
import com.Reviews.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@CrossOrigin("http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;
    @Autowired GameService gameService;
    @GetMapping("/{genre_id}")
    public Optional<Genre> getGenre(@PathVariable Long genre_id){
        return genreService.getGenreById(genre_id);
    }
    @GetMapping("/list")
    public List<Genre> getAllGenres(){
        return genreService.getAllGenres();
    }
    @GetMapping("/{genre_id}/games")
    public GamesResponse getAllGamesByGenre(@PathVariable Long genre_id,@RequestParam(defaultValue = "0") int page){
        Optional<Genre> genre = genreService.getGenreById(genre_id);
        //Converting Set to List to be able to use gamesByPage method
        Set<Game> gameSet = genre.get().getGames();
        List<Game> gameList = new ArrayList<>(gameSet);
        return gameService.gamesByPage(page,gameList);
    }


}
