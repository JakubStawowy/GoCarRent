package com.example.gocarrentspringbootapplication.security.controllers;

import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public final class LogoutController {

    private final UserRepository userRepository;

    @Autowired
    public LogoutController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping(value = "/logout")
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
