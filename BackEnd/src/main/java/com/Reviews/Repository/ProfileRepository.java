package com.Reviews.Repository;

import com.Reviews.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Optional<Profile> findByUsername(String username);
}
