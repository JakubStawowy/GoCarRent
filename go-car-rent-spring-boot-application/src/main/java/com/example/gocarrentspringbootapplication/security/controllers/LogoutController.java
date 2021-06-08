package com.example.gocarrentspringbootapplication.security.controllers;

import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
@RestController
@RequestMapping(value = "/api")
public final class LogoutController {

    private final UserRepository userRepository;

    @Autowired
    public LogoutController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping(value = EndpointRepository.LOGOUT_ENDPOINT)
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
