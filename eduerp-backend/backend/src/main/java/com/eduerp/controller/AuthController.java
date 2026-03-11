package com.eduerp.controller;

import com.eduerp.dto.request.LoginRequest;
import com.eduerp.dto.response.ApiResponse;
import com.eduerp.dto.response.JwtResponse;
import com.eduerp.entity.User;
import com.eduerp.service.AuthService;

import com.eduerp.security.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    // public AuthController(AuthService authService) {
    //     this.authService = authService;
    // }

    private final JwtUtil jwtUtil;

public AuthController(AuthService authService, JwtUtil jwtUtil) {
    this.authService = authService;
    this.jwtUtil = jwtUtil;
}

    // =========================
    // LOGIN
    // =========================
   @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    Optional<User> userOptional =
            authService.validateUser(request.getEmail(), request.getPassword());

    if (userOptional.isEmpty()) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse(false, "Invalid email or password"));
    }

    User user = userOptional.get();

    // 🔐 REAL JWT
    String token = jwtUtil.generateToken(user.getEmail());

    return ResponseEntity.ok(
            new JwtResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole().getName(),
                    token
            )
    );
}

    // =========================
    // SIGNUP
    // =========================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (authService.userExists(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "User already exists"));
        }

        authService.register(user);

        return ResponseEntity.ok(
                new ApiResponse(true, "Registration successful")
        );
    }
}