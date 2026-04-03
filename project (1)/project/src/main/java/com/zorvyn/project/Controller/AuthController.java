package com.zorvyn.project.Controller;

import com.zorvyn.project.Dto.LoginRequest;
import com.zorvyn.project.Dto.RegisterRequest;
import com.zorvyn.project.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements IAuthController {

    private final AuthService authService;

    /**
     * Handles user login.
     *
     * Accepts user credentials and returns a response (e.g., token or success message).
     * Returns HTTP 200 if login is successful.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Handles new user registration.
     *
     * Creates a new user in the system with the provided details.
     * Returns HTTP 201 when the user is successfully created.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        String response = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}