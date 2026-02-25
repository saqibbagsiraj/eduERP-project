package com.eduerp.service.impl;

import com.eduerp.entity.User;
import com.eduerp.repository.UserRepository;
import com.eduerp.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> login(String email) {
        return userRepository.findByEmail(email);
    }
}