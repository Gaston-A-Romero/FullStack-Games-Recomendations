package com.Reviews.Repository;

import com.Reviews.DTO.Likes;
import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    boolean existsByAuthorAndReview(Profile profile, Review review);
}
