package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.User;
import com.example.gocarrentspringbootapplication.models.UserDetails;
import com.example.gocarrentspringbootapplication.repositories.UserRepository;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"", "/"})
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}/edit", consumes = "application/json")
    public ResponseEntity<String> editUser(@RequestBody UserDetails details, @PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserDetails(details);
            Logger.getGlobal().log(Level.INFO, details.getName());
            Logger.getGlobal().log(Level.INFO, user.getUserDetails().getName());
            userRepository.save(optionalUser.get());
            return new ResponseEntity<>("User "+user.getUserDetails().getName()+" "+user.getUserDetails().getSurname()+" edited successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
