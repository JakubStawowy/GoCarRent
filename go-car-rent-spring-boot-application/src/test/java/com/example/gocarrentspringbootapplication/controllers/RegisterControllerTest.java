package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.TestUserProvider;
import com.example.gocarrentspringbootapplication.models.User;
import com.example.gocarrentspringbootapplication.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class RegisterControllerTest {

    User testUser;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    RegisterController registerController;

    @BeforeEach
    void setUp() {
        testUser = TestUserProvider.getTestUser();
    }

    @Test
    void registerNewUser() {
        assertEquals(HttpStatus.OK, registerController.registerUser(testUser).getStatusCode());
    }

    @Test
    void registerExistingUser() {
        Optional<User> optionalUser = Optional.of(testUser);
        Mockito.when(userRepository.getUserByEmail(testUser.getEmail())).thenReturn(optionalUser);
        assertEquals(HttpStatus.CONFLICT, registerController.registerUser(testUser).getStatusCode());
    }
}