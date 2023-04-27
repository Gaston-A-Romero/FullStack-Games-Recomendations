package com.Reviews.Services;
import com.Reviews.DTO.*;
import com.Reviews.Repository.FeedRepository;
import com.Reviews.Repository.ProfileRepository;
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
    public Profile editProfile(Profile profile) {
        Profile editedProfile = findById(profile.getId_profile()).get();

        if (profileRepository.findByUsername(profile.getUsername()).isPresent()){
            throw new RuntimeException("Username already exist");
        }
        editedProfile.setUsername(profile.getUsername());
        editedProfile.setDescription(profile.getDescription());
        editedProfile.setProfile_picture(profile.getProfile_picture());
        editedProfile.setSteam_account(profile.getSteam_account());
        editedProfile.setEpic_account(profile.getEpic_account());
        editedProfile.setPsn_account(profile.getPsn_account());
        editedProfile.setXbox_account(profile.getXbox_account());
        editedProfile.setNintendo_account(profile.getNintendo_account());
        editedProfile.setOther_account(profile.getOther_account());
        return profileRepository.save(editedProfile);
    }
    public String addFavGames(Long id,List<Game> games) {
        Optional<Profile> profile = findById(id);
        Set<Game> prof_games = profile.get().getFavorite_games();
        for (Game game: games) {
            if (profile.get().getFavorite_games().size() > 7){
                throw new ArrayIndexOutOfBoundsException();
            }
            prof_games.add(game);
        }
        profileRepository.save(profile.get());
        return "Games added to profile";
    }

    //Review methods
    public String addReview(Long idProfile, Review review) {
        Optional<Profile> profile = findById(idProfile);
        List<Review> profile_Reviews = profile.get().getReviews();
        Review added_review = reviewService.addReview(profile.get(),review);
        profile_Reviews.add(added_review);
        profileRepository.save(profile.get());
        return "Review added";

    }
    public String delReview(Long id_profile,Long idReview) {
        Optional<Profile> profile = findById(id_profile);
        Optional<Review> review = reviewService.getReview(idReview);
        if(!isYourReview(profile.get(),review.get().getId_review())){
            throw new RuntimeException("You cant delete this review because is not yours");
        }

        List<Review> reviews = profile.get().getReviews();
        reviews.removeIf(r -> r.getId_review().equals(idReview));
        profileRepository.save(profile.get());

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
    public String updateReview(Long idProfile, Review review) {
        Optional<Profile> profile = findById(idProfile);
        Optional<Review> review_to_update = reviewService.getReview(review.getId_review());
        if (isYourReview(profile.get(),review_to_update.get().getId_review())){
            reviewService.updateReview(review,review_to_update.get());
            return "Review updated";
        }
        return "Review couldn't be updated";

    }

    //Comment methods

    public String addComment(Long idProfile, Long idReview, Comment comment) {
        Optional<Profile> profile = findById(idProfile);
        Optional<Review> review = reviewService.getReview(idReview);
        reviewService.addComment(profile.get(),review.get(),comment);
        return "Comment added";
    }

    public String editComment(Long idProfile, Long idReview,Comment comment) {
        Optional<Profile> profile = findById(idProfile);
        if (isYourComment(profile.get(),comment)){
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

    public String delComment(Long idProfile, Long idReview, Long idComment) {
        Optional<Profile> profile = findById(idProfile);
        Optional<Comment> comment = commentService.getComment(idComment);
        if (isYourComment(profile.get(),comment.get())){
            reviewService.delComment(idReview,comment.get());
        }
        return "Comment is deleted";
    }

    // Like methods
    public String likeReview(Long idProfile, Long idReview) {
        Optional<Profile> profile = findById(idProfile);
        Optional<Review> review = reviewService.getReview(idReview);
        if (isYourReview(profile.get(),idReview)){
            throw new RuntimeException("You cant like your own Review");
        }
        if (likeService.alreadyGiveLike(profile.get(),review.get())){
            throw new RuntimeException("You already like this Review");
        }
        likeService.createLike(profile.get(),review.get());
        reviewService.liked(review.get());
        return "You liked this review: "+ review.get().getTitle_review();
    }

    public String removeLike(Long idProfile,Long id_review, Long idLike) {
        Optional<Profile> profile = findById(idProfile);
        Optional<Likes> like = likeService.getLike(idLike);
        if (like.isEmpty()){
            throw new RuntimeException("Like not found");
        }
        if (like.get().getAuthor() == profile.get() && like.get().getReview().getId_review() == id_review){
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
