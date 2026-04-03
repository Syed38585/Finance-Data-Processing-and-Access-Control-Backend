package com.zorvyn.project.Service;

import com.zorvyn.project.Dto.LoginRequest;
import com.zorvyn.project.Dto.RegisterRequest;
import com.zorvyn.project.Enums.Role;
import com.zorvyn.project.Enums.Status;
import com.zorvyn.project.Model.User;
import com.zorvyn.project.Repository.UserRepo;
import com.zorvyn.project.Security.CustomUserDetailsService;
import com.zorvyn.project.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    /**
     * Registers a new user.
     *
     * Checks if a user with the given email already exists.
     * If not, creates a new user with encoded password and default role/status.
     * Returns a success message after saving the user.
     */
    public String register(RegisterRequest request) {

        if (userRepo.findByEmailIgnoreCase(request.email()).isPresent()) {
            throw new IllegalArgumentException("User already exists with this email");
        }

        User user = new User();
        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        user.setDeleted(false);

        userRepo.save(user);

        return "User registered successfully";
    }

    /**
     * Authenticates a user and generates a JWT token.
     *
     * Validates the provided credentials using AuthenticationManager.
     * If authentication succeeds, loads user details and generates a token.
     * Returns the generated JWT token.
     */
    public String login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());

        return jwtUtil.generateToken(userDetails);
    }
}