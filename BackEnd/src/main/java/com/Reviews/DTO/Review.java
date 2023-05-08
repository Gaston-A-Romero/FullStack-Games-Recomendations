package com.Reviews.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_review;
    private String title_review;
    private String about_game;
    private String body_review;
    private Integer game_score;
    private Boolean recommended;
    private Integer likes = 0;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile",referencedColumnName = "id_profile")
    private Profile author;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> commentList;
    @ManyToOne()
    @JoinColumn(name = "id_game",referencedColumnName = "id_game")
    private Game game_reviewed;

}
