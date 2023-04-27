package com.Reviews.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feed {
    @Id
    private Integer id_feed;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviewsFeed;
    @OneToOne
    private Review lastReview;
}
