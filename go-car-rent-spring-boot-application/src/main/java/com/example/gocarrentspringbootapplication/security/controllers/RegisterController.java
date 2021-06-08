package com.example.gocarrentspringbootapplication.security.controllers;

import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
@RestController
@RequestMapping(value = RegisterController.BASE_ENDPOINT)
public final class RegisterController {

    public static final String BASE_ENDPOINT = "/api";
    private final UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = EndpointRepository.REGISTER_USER_ENDPOINT, consumes = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if(userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>(
                "User with email: " + user.getEmail() + " already exists",
                    HttpStatus.CONFLICT
            );
        }
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        userRepository.save(user);
        return new ResponseEntity<>(
            "User " + user.getUserDetails().getName() + " " + user.getUserDetails().getSurname() + " registered successfully",
                HttpStatus.OK
        );
    }
}
