package com.Reviews.Services;

import com.Reviews.DTO.Game;
import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import com.Reviews.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ReviewService reviewService;
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
        reviewService.delReview(review.get());
        return "Review deleted";
    }

    private boolean isYourReview(Profile profile, Long idReview) {
        List<Review> reviewList = profile.getReviews();
        if (reviewList.contains(reviewService.getReview(idReview))){
            return true;
        }
        return false;
    }

    public String updateReview(Long idProfile, Review review) {
        //TODO
        return "Review updated";
    }
}
