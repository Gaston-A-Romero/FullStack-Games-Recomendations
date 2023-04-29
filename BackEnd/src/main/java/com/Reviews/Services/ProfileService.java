package com.Reviews.Services;
import com.Reviews.DTO.*;
import com.Reviews.Repository.FeedRepository;
import com.Reviews.Repository.ProfileRepository;
import com.Reviews.Security.Token.Token;
import com.Reviews.Security.Token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private LikeService likeService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private TokenRepository tokenRepository;

    // Token verification
    private Profile verifyToken(String token) {
        Optional<Token> token_user = tokenRepository.findByToken(token.substring(7));
        if (token_user.isEmpty()){
            throw new NullPointerException("Token not found in database, cant get information fo the profile");
        }
        if (token_user.get().isExpired()){
            throw new RuntimeException("Token is expired");
        }
        return token_user.get().getUser().getUser_profile();
    }

    //Profile methods
    public Profile getProfileByName(String username) {
        Optional<Profile> profile = profileRepository.findByUsername(username);
        if (profile.isEmpty()){
            throw new NullPointerException("No profile with that username was found");
        }
        return profile.get();
    }
    public Optional<Profile> findById(Long idProfile) {
        Optional<Profile> profile = profileRepository.findById(idProfile);
        if (profile.isEmpty()){
            throw new NullPointerException("Profile not found");
        }
        return profile;
    }
    public Profile editProfile(String token, Profile profile) {
        Profile profile_token = verifyToken(token);
        if (profile_token.getId_profile() != profile.getId_profile()){
            throw new RuntimeException("Not your profile");
        }
        if (profileRepository.findByUsername(profile.getUsername()).isPresent()){
            throw new RuntimeException("Username is already in use");
        }
        profile_token.setUsername(profile.getUsername());
        profile_token.setDescription(profile.getDescription());
        profile_token.setProfile_picture(profile.getProfile_picture());
        profile_token.setSteam_account(profile.getSteam_account());
        profile_token.setEpic_account(profile.getEpic_account());
        profile_token.setPsn_account(profile.getPsn_account());
        profile_token.setXbox_account(profile.getXbox_account());
        profile_token.setNintendo_account(profile.getNintendo_account());
        profile_token.setOther_account(profile.getOther_account());
        return profileRepository.save(profile_token);
    }



    public String addFavGames(String token,List<Game> games) {
        Profile profile = verifyToken(token);
        Set<Game> prof_games = profile.getFavorite_games();
        for (Game game: games) {
            if (profile.getFavorite_games().size() > 7){
                throw new ArrayIndexOutOfBoundsException();
            }
            prof_games.add(game);
        }
        profileRepository.save(profile);
        return "Games added to profile";
    }

    //Review methods
    public String addReview(String token, Review review) {
        Profile profile = verifyToken(token);
        List<Review> profile_Reviews = profile.getReviews();
        Review added_review = reviewService.addReview(profile,review);
        profile_Reviews.add(added_review);
        profileRepository.save(profile);
        return "Review added";

    }
    public String delReview(String token,Long idReview) {
        Profile profile = verifyToken(token);
        Optional<Review> review = reviewService.getReview(idReview);
        if(!isYourReview(profile,review.get().getId_review())){
            throw new RuntimeException("You cant delete this review because is not yours");
        }
        List<Review> reviews = profile.getReviews();
        reviews.removeIf(r -> r.getId_review().equals(idReview));
        profileRepository.save(profile);

        reviewService.delReview(review.get().getId_review());
        return "Review deleted";
    }
    private boolean isYourReview(Profile profile, Long idReview) {
        List<Review> reviewList = profile.getReviews();
        for (Review review:reviewList) {
            if (review.getId_review() == idReview){
                return true;
            }
        }
        return false;

    }
    public String updateReview(String token, Review review) {
        Profile profile = verifyToken(token);
        Optional<Review> review_to_update = reviewService.getReview(review.getId_review());
        if (isYourReview(profile,review_to_update.get().getId_review())){
            reviewService.updateReview(review,review_to_update.get());
            return "Review updated";
        }
        return "Review couldn't be updated";
    }

    //Comment methods

    public String addComment(String token, Long idReview, Comment comment) {
        Profile profile = verifyToken(token);
        Optional<Review> review = reviewService.getReview(idReview);
        reviewService.addComment(profile,review.get(),comment);
        return "Comment added";
    }

    public String editComment(String token, Long idReview,Comment comment) {
        Profile profile = verifyToken(token);
        if (isYourComment(profile,comment)){
            reviewService.editComment(idReview,comment);
        }
        return "Comment is updated";
    }

    private boolean isYourComment(Profile profile, Comment comment) {
        if (profile.getId_profile() != comment.getAuthor().getId_profile()){
            throw new RuntimeException("This is not your comment");
        }
        return true;
    }

    public String delComment(String token, Long idReview, Long idComment) {
        Profile profile = verifyToken(token);
        Optional<Comment> comment = commentService.getComment(idComment);
        if (isYourComment(profile,comment.get())){
            reviewService.delComment(idReview,comment.get());
        }
        return "Comment is deleted";
    }

    // Like methods
    public String likeReview(String token, Long idReview) {
        Profile profile = verifyToken(token);
        Optional<Review> review = reviewService.getReview(idReview);
        if (isYourReview(profile,idReview)){
            throw new RuntimeException("You cant like your own Review");
        }
        if (likeService.alreadyGiveLike(profile,review.get())){
            throw new RuntimeException("You already like this Review");
        }
        likeService.createLike(profile,review.get());
        reviewService.liked(review.get());
        review.get().getAuthor().setScore_as_reviewer(review.get().getAuthor().getScore_as_reviewer() + 1);
        profileRepository.save(review.get().getAuthor());
        return "You liked this review: "+ review.get().getTitle_review();
    }

    public String removeLike(String token,Long id_review, Long idLike) {
        Profile profile = verifyToken(token);
        Optional<Likes> like = likeService.getLike(idLike);
        if (like.isEmpty()){
            throw new RuntimeException("Like not found");
        }
        if (like.get().getAuthor() == profile && like.get().getReview().getId_review() == id_review){
            likeService.delLike(like.get());
        }
        return "Like eliminated";
    }

    public List<Review> refreshFeed() {
        List<Review> feedList = feedRepository.getReferenceById(1).getReviewsFeed();
        if (feedList.size() < 50){
            Collections.reverse(feedList);
            return feedList;
        }
        List<Review> paginatedList = feedList.subList(feedList.size()-50,feedList.size());
        Collections.reverse(paginatedList);
        return paginatedList;

    }
}
