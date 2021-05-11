package com.example.gocarrentspringbootapplication.api.security;

import com.example.gocarrentspringbootapplication.impl.models.User;

public interface IAuthorizeService {
    boolean authorizeUserWithIdAndPassword(Long id, String password);
    User authorizeUserWithEmailAndPassword(String email, String password);
}
