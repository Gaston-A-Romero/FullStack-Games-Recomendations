package com.Reviews.Repository;

import com.Reviews.Model.Likes;
import com.Reviews.Model.Profile;
import com.Reviews.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    boolean existsByAuthorAndReview(Profile profile, Review review);
}
