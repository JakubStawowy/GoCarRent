package com.example.gocarrentspringbootapplication.security.api;

import com.example.gocarrentspringbootapplication.data.po.User;

public interface ITokenProvider {
    String generateUserToken(User user);
}
