package com.example.gocarrentspringbootapplication.security.api;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public interface IAuthenticationTokenProvider<T extends AbstractAuthenticationToken> {
    T getAuthenticationToken(String header);
}
