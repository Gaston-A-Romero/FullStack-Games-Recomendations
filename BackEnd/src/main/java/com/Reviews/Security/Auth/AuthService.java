package com.Reviews.Security.Auth;

import com.Reviews.Exceptions.ControlException;
import com.Reviews.Security.ConfigJWT.JwtService;
import com.Reviews.Model.Profile;
import com.Reviews.Repository.UserRepository;
import com.Reviews.Security.Token.Token;
import com.Reviews.Security.Token.TokenRepository;
import com.Reviews.Security.Token.TokenType;
import com.Reviews.Security.User.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.Reviews.Security.User.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    private final EmailAuthService emailAuthService;
    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;



    public AuthResponse register(RegisterRequest request) throws Exception {
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new ControlException("Email already in use");
        }
        if (request.getEmail().isEmpty()){
            throw new ControlException("Mail cant be empty");
        }
        if (request.getPassword().isEmpty()){
            throw new ControlException("Password cant be empty");
        }
        if(request.getEmail().equals("gastiromero@hotmail.com")){
            var profile = new Profile();
            var user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(UserRole.ADMIN)
                    .is_enabled(true)
                    .user_profile(profile)
                    .build();

            var saved_user = userRepository.save(user);
            var jwtToken = jwtService.generateToken(saved_user);
            var refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(saved_user, jwtToken);
            return AuthResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .expiration_date(jwtService.extractExpiration(jwtToken))
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
            var jwtToken = jwtService.generateToken(saved_user);
            var refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(saved_user, jwtToken);


            System.out.println(activationIdCode);
            emailAuthService.sendActivationEmail(user,activationIdCode);

            return AuthResponse.builder()
                    .accessToken(jwtToken)
                    .expiration_date(jwtService.extractExpiration(jwtToken))
                    .refreshToken(refreshToken)
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
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .expiration_date(jwtService.extractExpiration(jwtToken))
                .build();
    }
    public String confirmActivation(ActivationRequest activationRequest) {

        Optional<User> user_to_activate = userRepository.findByEmail(activationRequest.getEmail());

        System.out.println(user_to_activate.isPresent() + user_to_activate.get().getEmail());
        if (user_to_activate.isPresent() && user_to_activate.get().getActivation_code().equals(activationRequest.getActivation_code())) {
            user_to_activate.get().set_enabled(true);
            userRepository.save(user_to_activate.get());
            return "You are now able to login normally";
        }
        return "Not able to confirm activation";
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(Math.toIntExact(user.getId_user()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        System.out.println(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IOException();
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .expiration_date(jwtService.extractExpiration(accessToken))
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}
