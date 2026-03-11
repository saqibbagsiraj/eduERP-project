package com.eduerp.service.impl;

import com.eduerp.entity.User;
import com.eduerp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void loginReturnsUserWhenEmailExists() {
        String email = "user@example.com";
        User user = User.builder().id(1L).email(email).build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = authService.login(email);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository).findByEmail(email);
    }

    @Test
    void loginReturnsEmptyWhenEmailNotFound() {
        String email = "shashank@gmail.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<User> result = authService.login(email);

        assertTrue(result.isEmpty());
        verify(userRepository).findByEmail(email);
    }
}
