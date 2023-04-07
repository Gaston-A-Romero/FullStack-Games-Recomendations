package com.Reviews.Controller;

import com.Reviews.DTO.Game;
import com.Reviews.DTO.Genre;
import com.Reviews.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController()
@RequestMapping("/api/v1/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;
    @PostMapping
    public ResponseEntity<Genre> addGenre(@RequestBody String name_genre){
        Genre new_genre = new Genre(name_genre);
        genreService.saveGenre(new_genre);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{genre_id}")
    public Optional<Genre> getGenre(@PathVariable Long genre_id){
        return genreService.getGenreById(genre_id);
    }
    @GetMapping("/list")
    public List<Genre> getAllGenres(){
        return genreService.getAllGenres();
    }
    @GetMapping("/{genre_id}/games")
    public Set<Game> getAllGamesByGenre(@PathVariable Long genre_id) throws Exception {
        Optional<Genre> genre = genreService.getGenreById(genre_id);
        if (!genre.isPresent()){
            throw new Exception("The genre searched doesn't exist.");
        }
        return genreService.getGenreById(genre_id).get().getGames();
    }


}
