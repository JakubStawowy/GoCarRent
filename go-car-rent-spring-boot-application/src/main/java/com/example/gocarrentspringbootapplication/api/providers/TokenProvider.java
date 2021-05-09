package com.example.gocarrentspringbootapplication.api.providers;

import com.example.gocarrentspringbootapplication.impl.models.User;

public interface TokenProvider {
    String generateUserToken(User user);
}
