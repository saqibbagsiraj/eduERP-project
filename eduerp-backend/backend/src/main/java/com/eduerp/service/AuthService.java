package com.eduerp.service;

import com.eduerp.entity.User;

import java.util.Optional;

public interface AuthService {

    Optional<User> login(String email);

    boolean userExists(String email);

    User register(User user);

    Optional<User> validateUser(String email, String password);   // ✅ ADD
}