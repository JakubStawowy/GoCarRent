package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.User;
import com.example.gocarrentspringbootapplication.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.services.JsonWebTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final JsonWebTokenProvider jsonWebTokenProvider;

    @Autowired
    public LoginController(UserRepository userRepository, JsonWebTokenProvider jsonWebTokenProvider) {
        this.userRepository = userRepository;
        this.jsonWebTokenProvider = jsonWebTokenProvider;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        Optional<User> userFoundedByEmail = userRepository.getUserByEmail(email);
        if(userFoundedByEmail.isPresent()) {
            Optional<User> user = userRepository.getUserByEmailAndPassword(email, BCrypt.hashpw(password, userFoundedByEmail.get().getSalt()));
            if(user.isPresent()) {
                user.get().setLogged(true);
                userRepository.save(user.get());
                return new ResponseEntity<>(jsonWebTokenProvider.generateToken(user.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(@RequestParam("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            user.get().setLogged(false);
            userRepository.save(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
