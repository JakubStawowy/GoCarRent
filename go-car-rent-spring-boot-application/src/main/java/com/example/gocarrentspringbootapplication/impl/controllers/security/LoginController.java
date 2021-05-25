package com.example.gocarrentspringbootapplication.impl.controllers.security;

import com.example.gocarrentspringbootapplication.api.providers.ITokenProvider;
import com.example.gocarrentspringbootapplication.api.security.IAuthorizeService;
import com.example.gocarrentspringbootapplication.impl.dto.LoginTransferObject;
import com.example.gocarrentspringbootapplication.impl.models.User;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public final class LoginController {

    private final UserRepository userRepository;
    private final ITokenProvider tokenProvider;
    private final IAuthorizeService authorizeService;

    @Autowired
    public LoginController(UserRepository userRepository, ITokenProvider tokenProvider, IAuthorizeService authorizeService) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.authorizeService = authorizeService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginTransferObject> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user;
        if ((user = authorizeService.authorizeUserWithEmailAndPassword(email, password)) != null) {
            user.setLogged(true);
            userRepository.save(user);
            return new ResponseEntity<>(new LoginTransferObject(
                    user.getId(),
                    tokenProvider.generateUserToken(user),
                    user.getRoles()
                    ), HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
