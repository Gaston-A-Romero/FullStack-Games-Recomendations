package com.Reviews.Controller;
import com.Reviews.DTO.Comment;
import com.Reviews.DTO.Game;
import com.Reviews.DTO.Profile;
import com.Reviews.DTO.Review;
import com.Reviews.Security.Token.Token;
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

    //Profile requests
    @GetMapping("/{id_profile}")
    public ResponseEntity<Optional<Profile>> getProfile(@PathVariable Long id_profile){
            return ResponseEntity.ok(profileService.findById(id_profile));
    }
    @GetMapping
    public Profile getProfileByName(@RequestParam String profile_name){
        return profileService.getProfileByName(profile_name);
    }
    @PutMapping("/edit")
    public ResponseEntity<Profile> editProfile(@RequestHeader("Authorization") String token,@RequestBody Profile profile){
        return ResponseEntity.ok(profileService.editProfile(token,profile));
    }
    @PostMapping("/fav_games")
    public ResponseEntity<String> addFavGames(@RequestHeader("Authorization") String token,@RequestBody List<Game> games){
        return ResponseEntity.ok(profileService.addFavGames(token,games));
    }
    @GetMapping("/{id_profile}/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long id_profile){
        return ResponseEntity.ok(profileService.findById(id_profile).get().getReviews());
    }

    //Review requests
    @PostMapping("/review")
    public ResponseEntity<String> addReview(@RequestHeader("Authorization") String token,@RequestBody Review review){
        return ResponseEntity.ok(profileService.addReview(token,review));
    }
    @DeleteMapping("/review/{id_review}/delete")
    public ResponseEntity<String> delReview(@RequestHeader("Authorization") String token, @PathVariable Long id_review){
        return ResponseEntity.ok(profileService.delReview(token,id_review));
    }
    @PutMapping("/review/{id_review}")
    public ResponseEntity<String> updateReview(@RequestHeader("Authorization") String token, @RequestBody Review review){
        return ResponseEntity.ok(profileService.updateReview(token,review));
    }
    // Like methods
    @PostMapping("/review/{id_review}/like")
    public ResponseEntity<String> likeReview(@RequestHeader("Authorization") String token,@PathVariable Long id_review){
        return ResponseEntity.ok(profileService.likeReview(token,id_review));
    }
    @DeleteMapping("/review/{id_review}/like/{id_like}")
    public ResponseEntity<String> delLike(@RequestHeader("Authorization") String token,@PathVariable Long id_review,@PathVariable Long id_like){
        return ResponseEntity.ok(profileService.removeLike(token,id_review,id_like));
    }

    // Comments requests
    @PostMapping("/review/{id_review}/comment")
    public ResponseEntity<String> addComment(@RequestHeader("Authorization") String token, @PathVariable Long id_review, @RequestBody Comment comment){
        return ResponseEntity.ok(profileService.addComment(token,id_review,comment));
    }
    @PutMapping("/review/{id_review}/comment/{id_comment}")
    public ResponseEntity<String> editComment(@RequestHeader("Authorization") String token,@PathVariable Long id_review,@RequestBody Comment comment){
        return ResponseEntity.ok(profileService.editComment(token,id_review,comment));
    }
    @DeleteMapping("/review/{id_review}/comment/{id_comment}")
    public ResponseEntity<String> delComment(@RequestHeader("Authorization") String token,@PathVariable Long id_review,@PathVariable Long id_comment){
        return ResponseEntity.ok(profileService.delComment(token,id_review,id_comment));
    }
    // Get feed method
    @GetMapping("/review/feed")
    public ResponseEntity<List<Review>> getFeed(){
        return ResponseEntity.ok(profileService.refreshFeed());
    }

}
