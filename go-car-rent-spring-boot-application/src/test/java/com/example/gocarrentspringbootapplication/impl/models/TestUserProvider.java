package com.example.gocarrentspringbootapplication.impl.models;

import com.example.gocarrentspringbootapplication.impl.components.UserRoles;

public class TestUserProvider {

    private final static String email = "john@email.com";
    private final static String password = "password";
    private final static UserRoles role = UserRoles.ROLE_USER;
    private final static String username = "John";
    private final static String surname = "Doe";
    private final static String phone = "123123123";
    private final static String photo = "no-photo";

    public static User getTestUser() {
        return new User(
                email,
                password,
                role,
                new UserDetails(
                        username,
                        surname,
                        phone,
                        photo
                )
        );
    }
}
