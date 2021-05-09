package com.example.gocarrentspringbootapplication.impl.dto;

import com.example.gocarrentspringbootapplication.impl.components.UserRoles;

import java.io.Serializable;

public class UserTransferObject implements Serializable {

    private final String name;
    private final String surname;
    private final String email;
    private final UserRoles role;

    public UserTransferObject(String name, String surname, String email, UserRoles role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public UserRoles getRole() {
        return role;
    }
}
