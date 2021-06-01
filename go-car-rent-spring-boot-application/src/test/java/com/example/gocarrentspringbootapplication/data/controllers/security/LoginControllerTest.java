package com.example.gocarrentspringbootapplication.data.controllers.security;

import com.example.gocarrentspringbootapplication.security.dto.LoginTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.TestUserProvider;
import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import com.example.gocarrentspringbootapplication.security.controllers.LoginController;
import com.example.gocarrentspringbootapplication.security.providers.JsonWebTokenProvider;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class LoginControllerTest {

    @Mock
    UserRepository userRepository;
    @Mock
    JsonWebTokenProvider jsonWebTokenProvider;

    @InjectMocks
    LoginController loginController;
    User testUser;

    @BeforeEach
    void setUp() {
        testUser = TestUserProvider.getTestUser();
        testUser.setSalt(BCrypt.gensalt());
        testUser.setId((long) 0);
        Optional<User> optionalUser = Optional.of(testUser);
        Mockito.when(userRepository.getUserByEmailAndPassword(
                testUser.getEmail(),
                BCrypt.hashpw(testUser.getPassword(), testUser.getSalt())
        )).thenReturn(optionalUser);
        Mockito.when(userRepository.getUserByEmail(testUser.getEmail())).thenReturn(optionalUser);
        Mockito.when(jsonWebTokenProvider.generateUserToken(testUser)).thenReturn("token");
    }

    @Test
    void loginUser() {
        ResponseEntity<LoginTransferObject> responseEntity = loginController.loginUser(testUser.getEmail(), testUser.getPassword());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, Objects.requireNonNull(responseEntity.getBody()).getUserId());
        assertEquals("token", responseEntity.getBody().getToken());
    }
}