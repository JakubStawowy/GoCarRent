package com.example.gocarrentspringbootapplication.impl.models;

import com.example.gocarrentspringbootapplication.impl.components.UserRoles;

public class TestUserProvider {

    public final static Long ID = (long) 1;
    public final static String EMAIL = "john@email.com";
    public final static String PASSWORD = "password";
    public final static UserRoles ROLE = UserRoles.ROLE_USER;
    public final static String USERNAME = "John";
    public final static String SURNAME = "Doe";
    public final static String PHONE = "123123123";
    public final static String PHOTO = "no-photo";

    public static User getTestUser() {
        return new User(
                EMAIL,
                PASSWORD,
                ROLE,
                new UserDetails(
                        USERNAME,
                        SURNAME,
                        PHONE,
                        PHOTO
                )
        );
    }
}
