package com.Reviews.Security.Auth;

import com.Reviews.Security.ConfigJWT.JwtService;
import com.Reviews.Security.User.Profile.Profile;
import com.Reviews.Security.User.Profile.ProfileRepository;
import com.Reviews.Security.User.UserRepository;
import com.Reviews.Security.User.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.Reviews.Security.User.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterRequest request) throws Exception {
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new Exception("Email already in use");
        }
        if (request.getEmail().isEmpty()){
            throw new IllegalArgumentException("Mail cant be empty");
        }
        if (request.getPassword().isEmpty()){
            throw new IllegalArgumentException("Password cant be empty");
        }
        if(request.getEmail().equals("gastiromero@hotmail.com")){
            var profile = new Profile();
            var user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(UserRole.ADMIN)
                    .user_profile(profile)
                    .build();

            var saved_user = userRepository.save(user);
            var jwtToken = jwtService.generateToken(saved_user);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .profile(profile)
                    .build();
        }
        else {
            var profile = new Profile();
            var user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(UserRole.USER)
                    .user_profile(profile)
                    .build();

            var saved_user = userRepository.save(user);
            var jwtToken = jwtService.generateToken(saved_user);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .profile(profile)
                    .build();
        }


    }


    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .profile(user.getUser_profile())
                .build();


    }
}
