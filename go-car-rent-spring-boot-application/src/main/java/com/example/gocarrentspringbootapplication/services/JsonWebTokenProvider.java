package com.example.gocarrentspringbootapplication.services;

import com.example.gocarrentspringbootapplication.keys.TokenKeyRepository;
import com.example.gocarrentspringbootapplication.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JsonWebTokenProvider {
    public String generateToken(final User user) {

        Key signingKey = new SecretKeySpec(TokenKeyRepository.getKey().getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
        String name = user.getUserDetails().getName()+" "+user.getUserDetails().getSurname();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRoles())
                .claim("name", name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+20000))
                .signWith(SignatureAlgorithm.HS512, signingKey)

                .compact();
    }
}
