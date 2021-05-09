package com.example.gocarrentspringbootapplication.impl.controllers.security;

import com.example.gocarrentspringbootapplication.api.providers.TokenProvider;
import com.example.gocarrentspringbootapplication.impl.models.User;
import com.example.gocarrentspringbootapplication.impl.dao.UserRepository;
import com.example.gocarrentspringbootapplication.impl.providers.JsonWebTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Autowired
    public LoginController(UserRepository userRepository, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Pair<Long, String>> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        Optional<User> userFoundedByEmail = userRepository.getUserByEmail(email);
        if(userFoundedByEmail.isPresent()) {
            Optional<User> user = userRepository.getUserByEmailAndPassword(email, BCrypt.hashpw(password, userFoundedByEmail.get().getSalt()));
            if(user.isPresent()) {
                user.get().setLogged(true);
                userRepository.save(user.get());
                Pair<Long, String> userIdWithToken = Pair.of(user.get().getId(), tokenProvider.generateUserToken(user.get()));
                return new ResponseEntity<>(userIdWithToken, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
