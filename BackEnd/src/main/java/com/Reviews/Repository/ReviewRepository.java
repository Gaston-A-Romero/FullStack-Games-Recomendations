package com.Reviews.Repository;

import com.Reviews.DTO.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
