package com.example.gocarrentspringbootapplication.security.enums;

public enum UserRoles {
    ROLE_ADMIN,
    ROLE_USER;
    public String getValueWithoutPrefix() {
        return this.toString().substring(this.toString().indexOf("_") + 1);
    }
}
