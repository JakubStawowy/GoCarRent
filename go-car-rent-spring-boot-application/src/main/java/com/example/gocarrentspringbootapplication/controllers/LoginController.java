package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.Log;
import com.example.gocarrentspringbootapplication.models.User;
import com.example.gocarrentspringbootapplication.repositories.LogRepository;
import com.example.gocarrentspringbootapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private final UserRepository userRepository;
    private final LogRepository logRepository;

    @Autowired
    public LoginController(UserRepository userRepository, LogRepository logRepository) {
        this.userRepository = userRepository;
        this.logRepository = logRepository;
    }

    @PostMapping(value = "/login")
    public User loginUser(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {
        User loggedUser = userRepository.getUserByEmailAndPassword(email, password);
        logRepository.save(new Log(
                session.getId(),
                loggedUser
        ));
        return loggedUser;
    }
}
