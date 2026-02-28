package com.eduerp.controller;

import com.eduerp.dto.request.LoginRequest;
import com.eduerp.entity.User;
import com.eduerp.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return authService.login(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}