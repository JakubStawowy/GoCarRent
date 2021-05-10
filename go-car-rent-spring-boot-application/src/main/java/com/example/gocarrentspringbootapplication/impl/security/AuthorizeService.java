package com.example.gocarrentspringbootapplication.impl.security;

import com.example.gocarrentspringbootapplication.api.security.IAuthorizeService;
import com.example.gocarrentspringbootapplication.impl.dao.UserRepository;
import com.example.gocarrentspringbootapplication.impl.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizeService implements IAuthorizeService {

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
