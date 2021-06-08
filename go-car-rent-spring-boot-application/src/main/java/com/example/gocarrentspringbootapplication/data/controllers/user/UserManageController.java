package com.example.gocarrentspringbootapplication.data.controllers.user;

import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.data.po.UserDetails;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
@RestController
@RequestMapping("/api/users")
public final class UserManageController {

    private final UserRepository userRepository;

    @Autowired
    public UserManageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping(path = EndpointRepository.EDIT_USER_ENDPOINT, consumes = "application/json")
    public ResponseEntity<?> editUser(@RequestBody UserDetails details, @PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            details.setId(user.getUserDetails().getId());
            user.setUserDetails(details);
            Logger.getGlobal().log(Level.INFO, details.getName());
            Logger.getGlobal().log(Level.INFO, user.getUserDetails().getName());
            userRepository.save(optionalUser.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
