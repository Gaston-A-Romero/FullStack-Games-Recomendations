package com.Reviews.Security.Auth;

import com.Reviews.Security.ConfigJWT.JwtService;
import com.Reviews.DTO.Profile;
import com.Reviews.Repository.UserRepository;
import com.Reviews.Security.User.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.Reviews.Security.User.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final EmailAuthService emailAuthService;

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
        if(request.getEmail().equals("MAIL FOR SUPERUSER,ONES CREATED CANNOT BE ACCESSED AGAIN BECAUSE THE MAIL IS ALREADY BEEN USED")){
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
            String activationIdCode = UUID.randomUUID().toString();
            var profile = new Profile();
            var user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(UserRole.USER)
                    .activation_code(activationIdCode)
                    .user_profile(profile)
                    .is_enabled(false)
                    .build();

            var saved_user = userRepository.save(user);

            System.out.println(activationIdCode);
            emailAuthService.sendActivationEmail(user,activationIdCode);

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
    public String confirmActivation(String email ,String activationCode) {
        Optional<User> user_to_activate = userRepository.findByEmail(email);
        if (!(user_to_activate.isPresent() && user_to_activate.get().getActivation_code().equals(activationCode))) {
            throw new UsernameNotFoundException("User Not found or Activation code is not correct");
        }
        user_to_activate.get().set_enabled(true);
        return "Your Account has been activated...";

    }
}
