package com.example.gocarrentspringbootapplication.filters;

import com.example.gocarrentspringbootapplication.keys.TokenKeyRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonWebTokenFilter extends BasicAuthenticationFilter {
    public JsonWebTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader("Authorization");

        if(header==null || !header.startsWith("Bearer ")){
            chain.doFilter(request, response);
            return;
        }

        try {

            UsernamePasswordAuthenticationToken result = getAuthenticationByToken(header);
            SecurityContextHolder.getContext().setAuthentication(result);

        } catch (ExpiredJwtException e) {

            Logger.getGlobal().log(Level.INFO, e.getMessage());
            SecurityContextHolder.clearContext();

        } finally {
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(final String header) throws ExpiredJwtException {

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(TokenKeyRepository.getKey().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(header.replace("Bearer ",""));

        String username = claimsJws.getBody().get("name").toString();
        String roles = claimsJws.getBody().get("role").toString();

        return new UsernamePasswordAuthenticationToken(
          username,
          null,
                Collections.singletonList(new SimpleGrantedAuthority(roles))
        );
    }
}
