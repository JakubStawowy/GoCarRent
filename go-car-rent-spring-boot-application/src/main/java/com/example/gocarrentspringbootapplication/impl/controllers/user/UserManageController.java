package com.example.gocarrentspringbootapplication.impl.controllers.user;

import com.example.gocarrentspringbootapplication.impl.dao.UserRepository;
import com.example.gocarrentspringbootapplication.impl.models.User;
import com.example.gocarrentspringbootapplication.impl.models.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public final class UserManageController {

    private final UserRepository userRepository;

    @Autowired
    public UserManageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping(path = "/{id}/edit", consumes = "application/json")
    public ResponseEntity<Boolean> editUser(@RequestBody UserDetails details, @PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            details.setId(user.getUserDetails().getId());
            user.setUserDetails(details);
            Logger.getGlobal().log(Level.INFO, details.getName());
            Logger.getGlobal().log(Level.INFO, user.getUserDetails().getName());
            userRepository.save(optionalUser.get());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
