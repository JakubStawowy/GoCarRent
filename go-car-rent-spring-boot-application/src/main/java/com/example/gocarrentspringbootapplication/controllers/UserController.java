package com.example.gocarrentspringbootapplication.controllers;

import com.example.gocarrentspringbootapplication.models.User;
import com.example.gocarrentspringbootapplication.models.UserDetails;
import com.example.gocarrentspringbootapplication.repositories.UserRepository;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @GetMapping("/users/{id}")
    @Nullable
    public User getUser(@PathVariable("id") Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            Logger.getGlobal().log(Level.INFO, optionalUser.get().getEmail());
        }else{
            Logger.getGlobal().log(Level.INFO, "Nie udalo sie");
        }
//        return optionalUser.orElse(null);
        return new User("email", "password", new UserDetails(
                "name", "surname", "phone", "image"
        ));
    }

    @PutMapping("/users/edit")
    public User editUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }
}
