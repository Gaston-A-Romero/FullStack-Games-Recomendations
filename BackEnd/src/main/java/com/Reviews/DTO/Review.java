package com.Reviews.DTO;

import com.Reviews.DTO.Game;
import com.Reviews.DTO.Comment;
import com.Reviews.DTO.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_review;
    private String title_review;
    private String body_review;
    private Integer score;
    private Boolean recommended;
    private Profile author;
    @OneToMany(mappedBy = "review",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> commentList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_game",referencedColumnName = "id_game")
    private Game game_reviewed;

}
