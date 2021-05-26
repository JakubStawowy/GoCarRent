package com.example.gocarrentspringbootapplication.impl.filters;

import com.example.gocarrentspringbootapplication.api.providers.IAuthenticationTokenProvider;
import com.example.gocarrentspringbootapplication.impl.providers.AuthenticationTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class JsonWebTokenFilter extends BasicAuthenticationFilter {

    private final IAuthenticationTokenProvider<UsernamePasswordAuthenticationToken> authenticationTokenProvider;

    public JsonWebTokenFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
        authenticationTokenProvider = new AuthenticationTokenProvider();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader("Authorization");

        if(header==null || !header.startsWith("Bearer ")){
            chain.doFilter(request, response);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken result = authenticationTokenProvider.getAuthenticationToken(header);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (ExpiredJwtException e) {
            Logger.getLogger(JsonWebTokenFilter.class.getName()).log(Level.INFO, e.getMessage());
            SecurityContextHolder.clearContext();
        } finally {
            chain.doFilter(request, response);
        }
    }
}
