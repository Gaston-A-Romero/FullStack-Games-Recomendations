package com.Reviews.Services;

import com.Reviews.DTO.Game;
import com.Reviews.DTO.Genre;
import com.Reviews.Repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }
    public Genre searchByName(String name){
        List<Genre> all_genres = getAllGenres();
        Genre searched = null;
        for (Genre genre: all_genres
             ) {
            if (genre.getGenre_name().equals(name)){
                //Despues sacar este print
                System.out.println("SI SON IGUALES");
                searched = genre;
            }
        }
        return searched;
    }
    public Optional<Genre> getGenreById(Long id){
        Optional<Genre> searched_genre = genreRepository.findById(id);
        return searched_genre;
    }

    public void saveGenre(Genre genre){
        genreRepository.save(genre);
    }


}
