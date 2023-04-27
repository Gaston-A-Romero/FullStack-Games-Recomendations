package com.Reviews.Controller;
import com.Reviews.DTO.Comment;
import com.Reviews.DTO.Game;
import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import com.Reviews.Services.ProfileService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    //Profile requests
    @GetMapping("/{id_profile}")
    public Optional<Profile> getProfile(@PathVariable Long id_profile){
        return profileService.findById(id_profile);
    }
    @GetMapping
    public Profile getProfileByName(@RequestParam String profile_name){
        return profileService.getProfileByName(profile_name);
    }
    @PutMapping("/{id_profile}")
    public ResponseEntity<Profile> editProfile(@RequestBody Profile profile){
        return ResponseEntity.ok(profileService.editProfile(profile));
    }
    @PostMapping("/fav_games")
    public ResponseEntity<String> addFavGames(@RequestParam Long id_profile,@RequestBody List<Game> games){
        return ResponseEntity.ok(profileService.addFavGames(id_profile,games));
    }
    @GetMapping("/{id_profile}/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long id_profile){
        return ResponseEntity.ok(profileService.findById(id_profile).get().getReviews());
    }

    //Review requests
    @PostMapping("/review")
    public ResponseEntity<String> addReview(@RequestParam Long id_profile,@RequestBody Review review){
        return ResponseEntity.ok(profileService.addReview(id_profile,review));
    }
    @DeleteMapping("/review/{id_review}/delete")
    public ResponseEntity<String> delReview(@RequestParam Long id_profile, @PathVariable Long id_review){
        return ResponseEntity.ok(profileService.delReview(id_profile,id_review));
    }
    @PutMapping("/review/{id_review}")
    public ResponseEntity<String> updateReview(@RequestParam Long id_profile, @RequestBody Review review){
        return ResponseEntity.ok(profileService.updateReview(id_profile,review));
    }
    // Like methods
    @PostMapping("/review/{id_review}/like")
    public ResponseEntity<String> likeReview(@RequestParam Long id_profile,@PathVariable Long id_review){
        return ResponseEntity.ok(profileService.likeReview(id_profile,id_review));
    }
    @DeleteMapping("/review/{id_review}/like/{id_like}")
    public ResponseEntity<String> delLike(@RequestParam Long id_profile,@PathVariable Long id_review,@PathVariable Long id_like){
        return ResponseEntity.ok(profileService.removeLike(id_profile,id_review,id_like));
    }

    // Comments requests
    @PostMapping("/review/{id_review}/comment")
    public ResponseEntity<String> addComment(@RequestParam Long id_profile, @PathVariable Long id_review, @RequestBody Comment comment){
        return ResponseEntity.ok(profileService.addComment(id_profile,id_review,comment));
    }
    @PutMapping("/review/{id_review}/comment/{id_comment}")
    public ResponseEntity<String> editComment(@RequestParam Long id_profile,@PathVariable Long id_review,@RequestBody Comment comment){
        return ResponseEntity.ok(profileService.editComment(id_profile,id_review,comment));
    }
    @DeleteMapping("/review/{id_review}/comment/{id_comment}")
    public ResponseEntity<String> delComment(@RequestParam Long id_profile,@PathVariable Long id_review,@PathVariable Long id_comment){
        return ResponseEntity.ok(profileService.delComment(id_profile,id_review,id_comment));
    }
    // Get feed method
    @GetMapping("/review/feed")
    public ResponseEntity<List<Review>> getFeed(){
        return ResponseEntity.ok(profileService.refreshFeed());
    }

}
