package com.Reviews.Security.User.Profile;
import com.Reviews.DTO.Game;
import com.Reviews.Security.User.Profile.Posts.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profile;
    private String username;
    private List<String> games_profiles = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "profile_game_top",
            joinColumns = {@JoinColumn(name = "id_game")},
            inverseJoinColumns = {@JoinColumn(name = "id_profile")}
    )
    private Set<Game> favorite_games = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "profile_game_to_play",
            joinColumns = {@JoinColumn(name = "id_game")},
            inverseJoinColumns = {@JoinColumn(name = "id_profile")}
    )
    private Set<Game> games_planned_to_play = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviews;



}
