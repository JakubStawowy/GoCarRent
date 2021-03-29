package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.User;
import com.example.gocarrentspringbootapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class RegisterController {

    private final UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    @Nullable
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
