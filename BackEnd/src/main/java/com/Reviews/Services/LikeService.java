package com.Reviews.Services;

import com.Reviews.DTO.Like;
import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import com.Reviews.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public boolean alreadyGiveLike(Profile profile, Review review) {
        return likeRepository.existsByAuthorAndReview(profile, review);
    }

    public void createLike(Profile profile, Review review) {
        Like like = new Like();
        like.setAuthor(profile);
        like.setReview(review);
        likeRepository.save(like);
    }

    public Optional<Like> getLike(Long idLike) {
        return likeRepository.findById(idLike);
    }

    public void delLike(Like like) {
        likeRepository.delete(like);
    }
}
