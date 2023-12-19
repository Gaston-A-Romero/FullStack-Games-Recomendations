package com.Reviews.Services;

import com.Reviews.Model.Likes;
import com.Reviews.Model.Profile;
import com.Reviews.Model.Review;
import com.Reviews.Repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Likes likes = new Likes();
        likes.setAuthor(profile);
        likes.setReview(review);
        likeRepository.save(likes);
    }

    public Optional<Likes> getLike(Long idLike) {
        return likeRepository.findById(idLike);
    }

    public void delLike(Likes likes) {
        likeRepository.delete(likes);
    }
}
