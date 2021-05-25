package com.example.gocarrentspringbootapplication.impl.dto;

import com.example.gocarrentspringbootapplication.impl.enums.UserRoles;
import com.example.gocarrentspringbootapplication.impl.models.User;

import java.io.Serializable;

public class UserTransferObject implements Serializable {

    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final UserRoles role;
/*
    public UserTransferObject(String name, String surname, String email, UserRoles role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }*/
    public UserTransferObject(User user) {
        name = user.getUserDetails().getName();
        surname = user.getUserDetails().getSurname();
        email = user.getEmail();
        role = user.getRoles();
        phone = user.getUserDetails().getPhone();
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

    public String getPhone() {
        return phone;
    }
}
