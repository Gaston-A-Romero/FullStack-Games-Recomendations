package com.Reviews.Services;

import com.Reviews.DTO.Profile;
import com.Reviews.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    public Profile getProfileByName(String username) {
        Optional<Profile> profileOptional = profileRepository.findAll()
                                                            .stream().filter(profile -> profile.getUsername().equals(username))
                                                            .findFirst();
        if (profileOptional.isPresent()) {
            return profileOptional.get();
        } else {
            throw new UsernameNotFoundException("There is no profile with that username");
        }
    }

    public Optional<Profile> findById(Long idProfile) {
        return profileRepository.findById(idProfile);
    }
}
