package com.eduerp.dto.response;

public class JwtResponse {

    private Long userId;
    private String username;
    private String email;
    private String role;
    private String token;

    // ✅ Default constructor (REQUIRED for JSON)
    public JwtResponse() {}

    // ✅ All args constructor
    public JwtResponse(Long userId, String username, String email, String role, String token) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    // ✅ Getters
    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    // ✅ Setters (Needed for flexibility)
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}