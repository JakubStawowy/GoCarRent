package com.example.gocarrentspringbootapplication.repositories;

public interface EndpointRepository {
    String REGISTER_USER_ENDPOINT = "/register";
    String LOGOUT_ENDPOINT = "/logout";
    String LOGIN_ENDPOINT = "/login";
    String TENANT_RENTS_ENDPOINT = "/tenant/{id}";
    String DELETE_RENT_ENDPOINT = "/{rentId}/delete";
    String BLOCKED_ANNOUNCEMENTS_ENDPOINT = "/blocked";
    String USER_ANNOUNCEMENTS_ENDPOINT = "/user/{id}";
    String ANNOUNCEMENT_ENDPOINT = "/{id}";
    String FILTERED_ANNOUNCEMENTS_ENDPOINT = "/filter";
    String ADD_ANNOUNCEMENTS_ENDPOINT = "/add";
    String EDIT_ANNOUNCEMENT_ENDPOINT = "/{id}/edit";
    String BLOCK_ANNOUNCEMENT_ENDPOINT = "/{id}/block";
    String UNLOCK_ANNOUNCEMENT_ENDPOINT = "/{id}/unlock";
    String REMOVE_ANNOUNCEMENT_ENDPOINT = "/{id}/remove";
    String USER_FEEDBACK_ENDPOINT = "/user/{id}";
    String ADD_FEEDBACK_ENDPOINT = "/add";
    String DELETE_FEEDBACK_ENDPOINT = "/{id}/delete";
    String EDIT_FEEDBACK_ENDPOINT = "/{id}/edit";
    String USER_ENDPOINT = "/{id}";
    String EDIT_USER_ENDPOINT = "/{id}/edit";
    String USER_MESSAGES_ENDPOINT = "/user";
    String ARCHIVE_MESSAGE_ENDPOINT = "/{id}/archive";
    String LOAD_MESSAGES_ENDPOINT = "/load";
    String SEND_01_ENDPOINT = "/send/01";
    String SEND_02_ENDPOINT = "/send/02";
    String SEND_03_ENDPOINT = "/send/03";
    String SEND_04_ENDPOINT = "/send/04";
    String SEND_05_ENDPOINT = "/send/05";
    String SEND_06_ENDPOINT = "/send/06";
}
