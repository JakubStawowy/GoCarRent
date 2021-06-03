package com.example.gocarrentspringbootapplication.security.providers;

import com.example.gocarrentspringbootapplication.security.api.IAuthenticationTokenProvider;
import com.example.gocarrentspringbootapplication.security.api.TokenKeyRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public final class AuthenticationTokenProvider implements IAuthenticationTokenProvider<UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken getAuthenticationToken(final String header) throws ExpiredJwtException {

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(TokenKeyRepository.KEY.getBytes(StandardCharsets.UTF_8))
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
