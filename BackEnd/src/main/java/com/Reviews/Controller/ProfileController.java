package com.Reviews.Controller;
import com.Reviews.DTO.Profile;
import com.Reviews.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @GetMapping("/{id_profile}")
    public Optional<Profile> getProfile(@PathVariable Long id_profile){
        return Optional.ofNullable(profileService.findById(id_profile).orElseThrow(() -> new RuntimeException("Profile not found")));
    }
    @PutMapping("/{id_profile}")
    public ResponseEntity<Profile> editProfile(@RequestBody Profile profile){
        return profileService.editProfile(profile);
    }

}
