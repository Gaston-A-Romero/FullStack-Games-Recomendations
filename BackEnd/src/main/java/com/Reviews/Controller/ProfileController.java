package com.Reviews.Controller;
import com.Reviews.DTO.Game;
import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import com.Reviews.Services.ProfileService;
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

    //Profile methods
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
    //Review methods
    @GetMapping("/{id_profile}/review")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long id_profile){
        return ResponseEntity.ok(profileService.findById(id_profile).get().getReviews());
    }

    @PostMapping("/review")
    public ResponseEntity<String> addReview(@RequestParam Long id_profile,@RequestBody Review review){
        return ResponseEntity.ok(profileService.addReview(id_profile,review));
    }
    @DeleteMapping("/review/{id_review}")
    public ResponseEntity<String> delReview(@RequestParam Long id_profile, @PathVariable Long id_review){
        return ResponseEntity.ok(profileService.delReview(id_profile,id_review));
    }
    @PutMapping("/review/{id_review}")
    public ResponseEntity<String> updateReview(@RequestParam Long id_profile, @RequestBody Review review){
        return ResponseEntity.ok(profileService.updateReview(id_profile,review));
    }
}
