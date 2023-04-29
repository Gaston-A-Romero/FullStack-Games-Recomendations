package com.Reviews.Services;

import com.Reviews.DTO.Comment;
import com.Reviews.DTO.Profile;
import com.Reviews.Exceptions.ContentNotFoundException;
import com.Reviews.Exceptions.ControlException;
import com.Reviews.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment,Profile profile){
        Comment new_comment = new Comment();
        Optional<Comment> parent_comment = commentRepository.findById(comment.getParent_comment().getId_comment());
        if (parent_comment.isPresent()){
            new_comment.setParent_comment(parent_comment.get());
        }
        new_comment.setAuthor(profile);
        new_comment.setBody(comment.getBody());
        commentRepository.save(new_comment);
        return new_comment;
    }

    public Comment editComment(Comment comment) {
        if (comment.getBody().isEmpty()){
            throw new ControlException("Your new comment is empty");
        }
        comment.setBody(comment.getBody());
        commentRepository.save(comment);
        return comment;

    }

    public Optional<Comment> getComment(Long idComment) {
        Optional<Comment> commentOptional = commentRepository.findById(idComment);
        if (commentOptional.isEmpty()){
            throw new ContentNotFoundException("Comment not found");
        }
        return commentOptional;
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
