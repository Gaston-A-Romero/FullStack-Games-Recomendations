package com.Reviews.DTO;
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
    private Integer game_score;
    private Boolean recommended;
    private Integer likes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile",referencedColumnName = "id_profile")
    private Profile author;
    @OneToMany(mappedBy = "review",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> commentList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_game",referencedColumnName = "id_game")
    private Game game_reviewed;

}
