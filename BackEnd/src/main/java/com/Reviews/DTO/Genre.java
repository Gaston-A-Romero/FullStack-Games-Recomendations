package com.Reviews.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_genre;
    private String genre_name;

    @ManyToMany(mappedBy = "genres")
    //prevents infinite recursion
    @JsonIgnore
    private Set<Game> games = new HashSet<>();
    public Genre(String genre_name) {
        this.genre_name = genre_name;
    }
}
