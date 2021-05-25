package com.example.gocarrentspringbootapplication.api.providers;

import com.example.gocarrentspringbootapplication.impl.models.User;

public interface ITokenProvider {
    String generateUserToken(User user);
}
