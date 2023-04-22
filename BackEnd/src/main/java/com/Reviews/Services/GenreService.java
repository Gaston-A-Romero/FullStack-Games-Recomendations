package com.Reviews.Services;
import com.Reviews.DTO.Genre;
import com.Reviews.Repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
                searched = genre;
            }
        }
        return searched;
    }
    public Optional<Genre> getGenreById(Long id){
        Optional<Genre> searched_genre = genreRepository.findById(id);
        if (searched_genre.isEmpty()){
            throw new RuntimeException("Genre with id: "+ id + "not found" );
        }
        return searched_genre;
    }
    public void saveGenre(Genre genre){
        genreRepository.save(genre);
    }

}
