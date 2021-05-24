package com.example.gocarrentspringbootapplication.impl.controllers;

import com.example.gocarrentspringbootapplication.impl.controllers.user.UserLoaderController;
import com.example.gocarrentspringbootapplication.impl.models.TestUserProvider;
import com.example.gocarrentspringbootapplication.impl.models.User;
import com.example.gocarrentspringbootapplication.impl.dao.repositories.UserRepository;
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
class UserControllerTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserLoaderController userController;

    User testUser;

    @BeforeEach
    void setUp() {
        testUser = TestUserProvider.getTestUser();

        Optional<User> optionalUser = Optional.of(testUser);
        Mockito.when(userRepository.findById((long) 0)).thenReturn(optionalUser);
    }

    @Test
    void getUser() {
        assertEquals(HttpStatus.OK, userController.getUser((long) 0).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, userController.getUser((long) 1).getStatusCode());
    }

//    @Test
//    void editUser() {
//        assertEquals(HttpStatus.OK, userController.editUser(testUser.getUserDetails(), (long) 0).getStatusCode());
//        assertEquals(HttpStatus.NOT_FOUND, userController.editUser(testUser.getUserDetails(), (long) 1).getStatusCode());
//    }
}