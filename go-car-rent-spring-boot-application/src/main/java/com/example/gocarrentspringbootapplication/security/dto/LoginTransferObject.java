package com.example.gocarrentspringbootapplication.security.dto;

import com.example.gocarrentspringbootapplication.data.enums.UserRoles;

import java.io.Serializable;

public class LoginTransferObject implements Serializable {

    private final Long userId;
    private final String token;
    private final UserRoles role;

    public LoginTransferObject(Long userId, String token, UserRoles role) {
        this.userId = userId;
        this.token = token;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public UserRoles getRole() {
        return role;
    }
}
