package com.Reviews.Services;

import com.Reviews.DTO.Comment;
import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import com.Reviews.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentService commentService;
    public Review addReview(Profile profile , Review review){
        Review new_review = new Review();
        new_review.setTitle_review(review.getTitle_review());
        new_review.setBody_review(review.getBody_review());
        new_review.setRecommended(review.getRecommended());
        new_review.setGame_score(review.getGame_score());
        new_review.setGame_reviewed(review.getGame_reviewed());
        new_review.setAuthor(profile);
        reviewRepository.save(new_review);

        return new_review;
    }
    public Optional<Review> getReview(Long idReview) {
        Optional<Review> review = reviewRepository.findById(idReview);
        if (review.isEmpty()){
            throw new NullPointerException("Review not found");
        }
        return review;
    }

    public void delReview(Review review) {
        reviewRepository.delete(review);
    }

    public void updateReview(Review reviewToUpdate) {
        reviewToUpdate.setTitle_review(reviewToUpdate.getTitle_review());
        reviewToUpdate.setBody_review(reviewToUpdate.getBody_review());
        reviewToUpdate.setRecommended(reviewToUpdate.getRecommended());
        reviewToUpdate.setGame_score(reviewToUpdate.getGame_score());
        reviewRepository.save(reviewToUpdate);
    }

    public void addComment(Profile profile, Review review, Comment comment, Long id_parent_comment) {
        review.getCommentList().add(commentService.addComment(comment, id_parent_comment,profile));
        reviewRepository.save(review);
    }

    public void editComment(Long id_review, Comment comment) {
        Optional<Review> review = getReview(id_review);
        List<Comment> commentList = review.get().getCommentList();
        for(int i = 0; i <= commentList.size(); i++){
            if (commentList.get(i).getId_comment() == comment.getId_comment()){
                commentService.editComment(comment);
            }
        }
    }
}
