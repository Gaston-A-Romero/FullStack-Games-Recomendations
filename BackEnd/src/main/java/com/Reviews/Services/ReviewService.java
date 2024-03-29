package com.Reviews.Services;
import com.Reviews.Model.*;
import com.Reviews.Exceptions.ContentNotFoundException;
import com.Reviews.Repository.FeedRepository;
import com.Reviews.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FeedRepository feedRepository;
    public Review addReview(Profile profile , Review review){
        Review new_review = new Review();
        new_review.setTitle_review(review.getTitle_review());
        new_review.setAbout_game(review.getAbout_game());
        new_review.setBody_review(review.getBody_review());
        new_review.setRecommended(review.getRecommended());
        new_review.setGame_score(review.getGame_score());
        new_review.setGame_reviewed(review.getGame_reviewed());
        new_review.setAuthor(profile);
        reviewRepository.save(new_review);

        Feed feed = feedRepository.getReferenceById(1);
        feed.getReviewsFeed().add(new_review);
        feed.setLastReview(new_review);
        feedRepository.save(feed);
        return new_review;

    }
    public Optional<Review> getReview(Long idReview) {
        Optional<Review> review = reviewRepository.findById(idReview);
        if (review.isEmpty()){
            throw new ContentNotFoundException("Review not found");
        }
        return review;
    }

    public void delReview(Long id_review) {
        reviewRepository.deleteById(id_review);
    }

    public void updateReview(Review review, Review reviewToUpdate) {
        reviewToUpdate.setTitle_review(review.getTitle_review());
        reviewToUpdate.setBody_review(review.getBody_review());
        reviewToUpdate.setAbout_game(review.getAbout_game());
        reviewToUpdate.setRecommended(review.getRecommended());
        reviewToUpdate.setGame_score(review.getGame_score());
        reviewRepository.save(reviewToUpdate);
    }

    public void addComment(Profile profile, Review review, Comment comment) {
        review.getCommentList().add(commentService.addComment(comment,profile));
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

    public void delComment(Long idReview,Comment comment) {
        List<Comment> commentList = getReview(idReview).get().getCommentList();
        for(int i = 0; i <= commentList.size(); i++){
            if (commentList.get(i).getId_comment() == comment.getId_comment()){
                commentList.remove(i);
                commentService.deleteComment(comment);
            }
        }
    }

    public void liked(Review review) {
        review.setLikes(review.getLikes() + 1);
        reviewRepository.save(review);
    }

    public Integer getReviewsOfGame(Game gameReviewed) {
        //Get all the reviews of a game
        List<Review> reviewList = reviewRepository.findAll()
                .stream()
                .filter(review -> review.getGame_reviewed().equals(gameReviewed))
                .collect(Collectors.toList());

        gameReviewed.setNumber_of_reviews(reviewList.size());
        int avg_score = 0;
        for (Review rev:reviewList) {
            avg_score += rev.getGame_score();
        }
        Integer avg = avg_score / gameReviewed.getNumber_of_reviews();
        return avg;
    }
}
