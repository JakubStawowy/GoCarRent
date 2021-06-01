package com.example.gocarrentspringbootapplication.security.api;

import com.example.gocarrentspringbootapplication.data.po.User;

public interface IAuthorizeService {
    boolean authorizeUserWithIdAndPassword(Long id, String password);
    User authorizeUserWithEmailAndPassword(String email, String password);
}
