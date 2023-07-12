package com.Reviews.Repository;

import com.Reviews.DTO.Game;
import com.Reviews.DTO.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
