package com.Reviews.Services;

import com.Reviews.DTO.Comment;
import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import com.Reviews.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment, Long id_parent_comment,Profile profile){
        Comment new_comment = new Comment();
        Optional<Comment> parent_comment = commentRepository.findById(id_parent_comment);
        if (parent_comment.isPresent()){
            new_comment.setParent_comment(parent_comment.get());
            List<Comment> childComments = parent_comment.get().getChild_comments();
            childComments.add(new_comment);
            commentRepository.save(parent_comment.get());
        }
        new_comment.setAuthor(profile);
        new_comment.setBody(comment.getBody());
        commentRepository.save(new_comment);
        return new_comment;
    }

    public Comment editComment(Comment comment) {
        if (comment.getBody().isEmpty()){
            throw new RuntimeException("Your new comment is empty");
        }
        comment.setBody(comment.getBody());
        commentRepository.save(comment);
        return comment;

    }
}
