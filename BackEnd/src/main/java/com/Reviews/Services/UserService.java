package com.Reviews.Services;

import com.Reviews.Model.Feed;
import com.Reviews.Model.Review;
import com.Reviews.Exceptions.ContentNotFoundException;
import com.Reviews.Repository.FeedRepository;
import com.Reviews.Repository.ReviewRepository;
import com.Reviews.Security.User.User;
import com.Reviews.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private FeedRepository feedRepository;

    public String deleteUser(Long id_user){
        Optional<User> user = userRepository.findById(id_user);
        if (user.isPresent()){
            userRepository.delete(user.get());
            return "User deleted";
        }
        return "User not found";
    }

    public List<User> findallusers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()){
            throw new ContentNotFoundException("User not found");
        }
        return user;
    }

    public String feed() {
        Feed feed = new Feed();
        feed.setId_feed(1);
        feedRepository.save(feed);
        return "Feed initialized";
    }
    // method to control the size of the feed
    public String updateFeed() {
        Feed feed = feedRepository.getReferenceById(1);
        if (feed.getReviewsFeed().size() > 1000){
            List<Review> new_list = feed.getReviewsFeed().subList(499, Math.toIntExact(feed.getLastReview().getId_review()));
            feed.setReviewsFeed(new_list);
            feed.setLastReview(new_list.get(-1));
            feedRepository.save(feed);
        }
        return "Feed updated";
    }
}
