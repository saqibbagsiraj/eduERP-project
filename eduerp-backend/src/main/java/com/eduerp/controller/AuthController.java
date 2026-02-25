package com.eduerp.controller;

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

    @GetMapping("/login")
    public User login(@RequestParam String email) {
        return authService.login(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}