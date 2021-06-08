package com.example.gocarrentspringbootapplication.security.controllers;

import com.example.gocarrentspringbootapplication.repositories.EndpointRepository;
import com.example.gocarrentspringbootapplication.repositories.OriginsRepository;
import com.example.gocarrentspringbootapplication.security.api.ITokenProvider;
import com.example.gocarrentspringbootapplication.security.api.IAuthorizeService;
import com.example.gocarrentspringbootapplication.security.dto.LoginTransferObject;
import com.example.gocarrentspringbootapplication.data.po.User;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = OriginsRepository.LOCALHOST_ORIGIN)
@RestController
@RequestMapping(LoginController.BASE_ENDPOINT)
public final class LoginController {

    public static final String BASE_ENDPOINT = "/api";
    private final UserRepository userRepository;
    private final ITokenProvider tokenProvider;
    private final IAuthorizeService authorizeService;

    @Autowired
    public LoginController(UserRepository userRepository, ITokenProvider tokenProvider, IAuthorizeService authorizeService) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.authorizeService = authorizeService;
    }

    @PostMapping(value = EndpointRepository.LOGIN_ENDPOINT)
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
