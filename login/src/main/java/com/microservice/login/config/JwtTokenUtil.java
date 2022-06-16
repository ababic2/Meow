package com.microservice.login.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.login.entity.TokenSubject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtTokenUtil implements Serializable {
    public static final int JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000; //One day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static String secret;
    JwtTokenUtil(@Value("${jwt.secret}") String secret){
        JwtTokenUtil.secret = secret;
    }

    public String extractSubject(String token){
        try {
            return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                    .build().verify(token)
                    .getSubject();
        }catch (TokenExpiredException e){
            return "Expired";
        }
    }
    public TokenSubject getTokenSubject(String token) throws JsonProcessingException, UnsupportedEncodingException {
        String extras = extractSubject(token);
        byte[] subject = Base64.getDecoder().decode(extras.getBytes());
        String decodedSubject = new String(subject,"UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(decodedSubject, TokenSubject.class);
    }

    public String createToken(TokenSubject tokenSubject) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String test = objectMapper.writeValueAsString(tokenSubject);
        String tests = Base64.getEncoder().encodeToString(test.getBytes());
        return JWT.create()
                .withSubject(tests)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtTokenUtil.JWT_TOKEN_VALIDITY))
                .sign(Algorithm.HMAC512(JwtTokenUtil.secret.getBytes()));
    }
}

