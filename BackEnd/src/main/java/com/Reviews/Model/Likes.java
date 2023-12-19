package com.Reviews.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_like;
    @ManyToOne(fetch = FetchType.LAZY)
    private Profile author;
    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;
}
