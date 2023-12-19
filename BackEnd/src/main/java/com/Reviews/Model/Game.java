package com.Reviews.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_game;
    private String title;
    private String platform;
    private String company;
    private String release_date;
    private Integer year;
    private String developer;
    private String picture;
    private Long public_score;
    private Integer number_of_reviews;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "game_genre",
            joinColumns = {@JoinColumn(name = "id_game")},
            inverseJoinColumns = {@JoinColumn(name = "id_genre")}
    )
    private Set<Genre> genres = new HashSet<>();
}
