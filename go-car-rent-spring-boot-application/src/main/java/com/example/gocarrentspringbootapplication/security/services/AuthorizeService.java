package com.example.gocarrentspringbootapplication.security.services;

import com.example.gocarrentspringbootapplication.security.api.IAuthorizeService;
import com.example.gocarrentspringbootapplication.data.dao.UserRepository;
import com.example.gocarrentspringbootapplication.data.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class AuthorizeService implements IAuthorizeService {

    private final UserRepository userRepository;

    @Autowired
    public AuthorizeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authorizeUserWithIdAndPassword(final Long id, final String password) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> user.getPassword().equals(BCrypt.hashpw(password, user.getSalt()))).orElse(false);
    }

    @Override
    @Nullable
    public User authorizeUserWithEmailAndPassword(final String email, final String password) {
        Optional<User> optionalUser = userRepository.getUserByEmail(email);
        if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(BCrypt.hashpw(password, optionalUser.get().getSalt()))) {
            return optionalUser.get();
        }
        return null;
    }
}
