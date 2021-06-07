package com.example.gocarrentspringbootapplication.data.controllers.user;

import com.example.gocarrentspringbootapplication.data.dto.UserTransferObject;
import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
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

    @GetMapping(EndpointRepository.USER_ENDPOINT)
    public ResponseEntity<UserTransferObject> getUser(@PathVariable("id") Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(user -> new ResponseEntity<>(new UserTransferObject(user), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
