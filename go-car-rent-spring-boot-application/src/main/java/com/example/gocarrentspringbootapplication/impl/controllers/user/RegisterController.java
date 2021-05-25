package com.example.gocarrentspringbootapplication.impl.controllers.user;

import com.example.gocarrentspringbootapplication.impl.models.User;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public final class RegisterController {

    private final UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if(userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity<>(
                "User with email: "+user.getEmail()+" already exists",
                    HttpStatus.CONFLICT
            );
        }
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        userRepository.save(user);
        return new ResponseEntity<>(
            "User "+user.getUserDetails().getName()+" "+user.getUserDetails().getSurname()+" registered successfully",
                HttpStatus.OK
        );
    }
}
