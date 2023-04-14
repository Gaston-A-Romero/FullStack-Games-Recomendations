package com.Reviews.Security.User.Profile;
import org.springframework.beans.factory.annotation.Autowired;
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
}
