package com.example.gocarrentspringbootapplication.impl.controllers.user;

import com.example.gocarrentspringbootapplication.impl.dto.UserTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.User;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public final class UserLoaderController {

    private final UserRepository userRepository;

    @Autowired
    public UserLoaderController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"", "/"})
    public List<UserTransferObject> getUsers(){
        List<UserTransferObject> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(new UserTransferObject(user)));
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTransferObject> getUser(@PathVariable("id") Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(user -> new ResponseEntity<>(new UserTransferObject(user), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
