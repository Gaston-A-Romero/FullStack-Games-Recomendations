package com.Reviews.DTO;
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
    private String description;
    private Integer score_as_reviewer;
    private String profile_picture;
    private String steam_account;
    private String epic_account;
    private String psn_account;
    private String xbox_account;
    private String nintendo_account;
    private String other_account;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "profile_game_top",
            joinColumns = {@JoinColumn(name = "id_game")},
            inverseJoinColumns = {@JoinColumn(name = "id_profile")}
    )
    private Set<Game> favorite_games = new HashSet<>();
    @OneToMany(mappedBy = "id_comment", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    private List<Review> reviews;



}
