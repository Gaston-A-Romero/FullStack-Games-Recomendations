package com.Reviews.Services;

import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import com.Reviews.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    public String addReview(Profile profile , Review review){
        Review new_review = new Review();
        new_review.setTitle_review(review.getTitle_review());
        new_review.setBody_review(review.getBody_review());
        new_review.setRecommended(review.getRecommended());
        new_review.setScore(review.getScore());
        new_review.setGame_reviewed(review.getGame_reviewed());
        new_review.setAuthor(profile);
        reviewRepository.save(review);
        return "Review added";
    }
}
