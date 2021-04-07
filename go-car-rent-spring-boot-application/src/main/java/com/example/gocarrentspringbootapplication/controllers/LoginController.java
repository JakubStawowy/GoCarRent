package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.Log;
import com.example.gocarrentspringbootapplication.models.User;
import com.example.gocarrentspringbootapplication.repositories.LogRepository;
import com.example.gocarrentspringbootapplication.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.services.JsonWebTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private final UserRepository userRepository;
    private final LogRepository logRepository;
    private final JsonWebTokenProvider jsonWebTokenProvider;

    @Autowired
    public LoginController(UserRepository userRepository, LogRepository logRepository, JsonWebTokenProvider jsonWebTokenProvider) {
        this.userRepository = userRepository;
        this.logRepository = logRepository;
        this.jsonWebTokenProvider = jsonWebTokenProvider;
    }

    @PostMapping(value = "/login")
    @Nullable
    public String loginUser(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {
        Optional<User> loggedUser = userRepository.getUserByEmailAndPassword(email, password);

        loggedUser.ifPresent(user->{
            logRepository.save(new Log(
                    session.getId(),
                    user
            ));
        });

        return loggedUser.map(jsonWebTokenProvider::generateToken).orElse(null);
    }

    @PostMapping(value = "/loginTest")
    public String test(@RequestParam("email") String email, @RequestParam("password") String password) {
        return "Logged: "+email+" "+password;
    }
}
