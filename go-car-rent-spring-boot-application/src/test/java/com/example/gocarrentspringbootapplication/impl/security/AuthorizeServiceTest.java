package com.example.gocarrentspringbootapplication.impl.security;

import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.impl.models.TestUserProvider;
import com.example.gocarrentspringbootapplication.impl.models.User;
import com.example.gocarrentspringbootapplication.impl.services.AuthorizeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class AuthorizeServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthorizeService authorizeService;
    private User testUser;
    @BeforeEach
    void setUp() {
        // GIVEN
        testUser = TestUserProvider.getTestUser();
        testUser.setSalt(BCrypt.gensalt());
        testUser.setPassword(BCrypt.hashpw(TestUserProvider.PASSWORD, testUser.getSalt()));

    }

    @Test
    void authorizeUserWithIdAndPassword() {

        // WHEN
        Mockito.when(userRepository.findById(TestUserProvider.ID)).thenReturn(Optional.of(testUser));
        Mockito.when(userRepository.findById(TestUserProvider.ID + 1)).thenReturn(Optional.empty());

        // THEN
        assertTrue(authorizeService.authorizeUserWithIdAndPassword(TestUserProvider.ID, TestUserProvider.PASSWORD));
        assertFalse(authorizeService.authorizeUserWithIdAndPassword(TestUserProvider.ID, "_" + TestUserProvider.PASSWORD));
        assertFalse(authorizeService.authorizeUserWithIdAndPassword(TestUserProvider.ID + 1, TestUserProvider.PASSWORD));

    }

    @Test
    void authorizeUserWithEmailAndPassword() {

        // WHEN
        Mockito.when(userRepository.getUserByEmail(TestUserProvider.EMAIL)).thenReturn(Optional.of(testUser));
        Mockito.when(userRepository.getUserByEmail("_" + TestUserProvider.EMAIL)).thenReturn(Optional.empty());

        // THEN
        assertEquals(testUser, authorizeService.authorizeUserWithEmailAndPassword(TestUserProvider.EMAIL, TestUserProvider.PASSWORD));
        assertNull(authorizeService.authorizeUserWithEmailAndPassword(TestUserProvider.EMAIL, "_" + TestUserProvider.PASSWORD));
        assertNull(authorizeService.authorizeUserWithEmailAndPassword("_" + TestUserProvider.EMAIL, TestUserProvider.PASSWORD));
    }
}