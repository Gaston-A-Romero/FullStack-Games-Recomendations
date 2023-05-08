package com.Reviews.DTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private boolean edited = false;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile author;
    @JsonIgnore
    @OneToMany(mappedBy = "parent_comment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> child_comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id_comment")
    private Comment parent_comment;


}
