package com.Reviews.Services;

import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import com.Reviews.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
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
}
