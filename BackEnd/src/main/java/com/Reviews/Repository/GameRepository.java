package com.Reviews.Repository;

import com.Reviews.DTO.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    //Using a query with Like in SQL
    List<Game> findByTitleContainingIgnoreCase(String title);
    List<Game> findByYear(Integer year);
    List<Game> findByPlatform(String platform);
}
