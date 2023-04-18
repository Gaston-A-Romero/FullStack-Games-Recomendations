package com.Reviews.DTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comment;
    private String body;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_review",referencedColumnName = "id_review")
    private Review review;

    @OneToMany(mappedBy = "parent_comment",fetch = FetchType.LAZY)
    private List<Comment> child_comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id_comment")
    private Comment parent_comment;


}
