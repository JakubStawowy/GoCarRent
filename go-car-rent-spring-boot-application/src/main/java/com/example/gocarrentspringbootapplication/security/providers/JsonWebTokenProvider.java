package com.example.gocarrentspringbootapplication.security.providers;

import com.example.gocarrentspringbootapplication.security.api.ITokenProvider;
import com.example.gocarrentspringbootapplication.repositories.TokenKeyRepository;
import com.example.gocarrentspringbootapplication.data.po.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JsonWebTokenProvider implements ITokenProvider {

    private static final String USERNAME_PREFIX = "name";
    private static final String ROLE_PREFIX = "role";
    private static final long TIMEOUT = 3600000;

    @Override
    public String generateUserToken(final User user) {

        Key signingKey = new SecretKeySpec(TokenKeyRepository.KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
        String name = user.getUserDetails().getName()+" "+user.getUserDetails().getSurname();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim(ROLE_PREFIX, user.getRoles())
                .claim(USERNAME_PREFIX, name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIMEOUT))
//                .setExpiration(new Date(System.currentTimeMillis()+10000))
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }
}
