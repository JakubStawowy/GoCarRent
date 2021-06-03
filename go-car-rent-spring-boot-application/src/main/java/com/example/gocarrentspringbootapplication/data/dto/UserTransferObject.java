package com.example.gocarrentspringbootapplication.data.dto;

import com.example.gocarrentspringbootapplication.data.enums.UserRoles;
import com.example.gocarrentspringbootapplication.data.po.User;

import java.io.Serializable;

public class UserTransferObject implements Serializable {

    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final UserRoles role;

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
