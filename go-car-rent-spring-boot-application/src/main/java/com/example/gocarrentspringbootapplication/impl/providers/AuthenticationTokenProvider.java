package com.example.gocarrentspringbootapplication.impl.providers;

import com.example.gocarrentspringbootapplication.api.providers.IAuthenticationTokenProvider;
import com.example.gocarrentspringbootapplication.impl.keys.TokenKeyRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AuthenticationTokenProvider implements IAuthenticationTokenProvider<UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken getAuthenticationToken(final String header) throws ExpiredJwtException {

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(TokenKeyRepository.getKey().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(header.replace("Bearer ",""));

        String username = claimsJws.getBody().get("name").toString();
        String roles = claimsJws.getBody().get("role").toString();
        Logger.getGlobal().log(Level.INFO, "ROLE: " + roles);
        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(roles))
        );
    }
}
