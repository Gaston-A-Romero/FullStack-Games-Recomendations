package com.Reviews.Security.User.Profile.Posts;

import com.Reviews.DTO.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_review;
    private String title_review;
    private String body_review;
    private Integer score;
    private Boolean recommended;
    @ManyToOne
    @JoinColumn(name = "id_game",referencedColumnName = "id_game")
    private Game game_reviewed;
}
