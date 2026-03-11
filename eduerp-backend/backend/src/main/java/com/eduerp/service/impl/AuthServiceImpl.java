package com.eduerp.service.impl;

import com.eduerp.entity.Role;
import com.eduerp.entity.User;
import com.eduerp.repository.RoleRepository;
import com.eduerp.repository.UserRepository;
import com.eduerp.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;   // ✅ Added

    // ✅ Inject BCrypt using constructor (BEST PRACTICE)
    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 🔐 Login validation (NEW METHOD)
    @Override
    public Optional<User> validateUser(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // ✅ Compare raw password with hashed password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    // Existing method (can keep for lookup if needed)
    @Override
    public Optional<User> login(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // 🔐 Signup with BCrypt hashing
    @Override
    public User register(User user) {

        if (user.getRole() == null || user.getRole().getId() == null) {
            throw new RuntimeException("Role is required");
        }

        Role role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);

        // ✅ HASH PASSWORD BEFORE SAVING
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}