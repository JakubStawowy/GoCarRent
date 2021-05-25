package com.example.gocarrentspringbootapplication.api.providers;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public interface IAuthenticationTokenProvider<T extends AbstractAuthenticationToken> {
    T getAuthenticationToken(String header);
}
