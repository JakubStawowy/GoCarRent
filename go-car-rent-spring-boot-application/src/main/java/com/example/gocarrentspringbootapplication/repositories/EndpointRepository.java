package com.example.gocarrentspringbootapplication.repositories;

public class EndpointRepository {
    public static final String INDEX_ENDPOINT = "";
    public static final String INDEX_ENDPOINT_SLASH = "/";
    public static final String REGISTER_USER_ENDPOINT = "/register";
    public static final String LOGOUT_ENDPOINT = "/logout";
    public static final String LOGIN_ENDPOINT = "/login";
    public static final String TENANT_RENTS_ENDPOINT = "/tenant/{id}";
    public static final String DELETE_RENT_ENDPOINT = "/{rentId}/delete";
    public static final String BLOCKED_ANNOUNCEMENTS_ENDPOINT = "/blocked";
    public static final String USER_ANNOUNCEMENTS_ENDPOINT = "/user/{id}";
    public static final String ANNOUNCEMENT_ENDPOINT = "/{id}";
    public static final String FILTERED_ANNOUNCEMENTS_ENDPOINT = "/filter";
    public static final String ADD_ANNOUNCEMENTS_ENDPOINT = "/add";
    public static final String EDIT_ANNOUNCEMENT_ENDPOINT = "/{id}/edit";
    public static final String BLOCK_ANNOUNCEMENT_ENDPOINT = "/{id}/block";
    public static final String UNLOCK_ANNOUNCEMENT_ENDPOINT = "/{id}/unlock";
    public static final String REMOVE_ANNOUNCEMENT_ENDPOINT = "/{id}/remove";
    public static final String USER_FEEDBACK_ENDPOINT = "/user/{id}";
    public static final String ADD_FEEDBACK_ENDPOINT = "/add";
    public static final String DELETE_FEEDBACK_ENDPOINT = "/{id}/delete";
    public static final String EDIT_FEEDBACK_ENDPOINT = "/{id}/edit";
    public static final String USER_ENDPOINT = "/{id}";
    public static final String EDIT_USER_ENDPOINT = "/{id}/edit";
    public static final String USER_MESSAGES_ENDPOINT = "/user";
    public static final String ARCHIVE_MESSAGE_ENDPOINT = "/{id}/archive";
    public static final String LOAD_MESSAGES_ENDPOINT = "/load";
    public static final String SEND_01_ENDPOINT = "/send/01";
    public static final String SEND_02_ENDPOINT = "/send/02";
    public static final String SEND_03_ENDPOINT = "/send/03";
    public static final String SEND_04_ENDPOINT = "/send/04";
    public static final String SEND_05_ENDPOINT = "/send/05";
    public static final String SEND_06_ENDPOINT = "/send/06";
    private EndpointRepository() {}
}
