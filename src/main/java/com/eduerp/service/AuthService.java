package com.eduerp.service;

import com.eduerp.entity.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(String email);
}