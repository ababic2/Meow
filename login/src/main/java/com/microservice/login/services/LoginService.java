package com.microservice.login.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.login.config.JwtTokenUtil;
import com.microservice.login.entity.*;
import com.microservice.login.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository clientRepository;

    public LoginService(UserRepository clientRepository, JwtTokenUtil jwtTokenUtil) {
        this.clientRepository = clientRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    private Token createJwt(int role, Long loggedUserId){
        TokenSubject tokenSubject = new TokenSubject(role, loggedUserId, (new Date(System.currentTimeMillis())).toString(), JwtTokenUtil.JWT_TOKEN_VALIDITY);
        String token = null;
        try {
            token = jwtTokenUtil.createToken(tokenSubject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Token(token);
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        String userName = loginRequest.getUserName();
        System.out.println("___________________________________");
        System.out.println(userName);
        User client = clientRepository.findByUserName(userName);
        if (client != null) {
            if (!loginRequest.getPassword().equals(client.getPassword()))
                return new ResponseEntity<>(new ErrorMessage("Invalid password!"), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(createJwt(client.getRole(), client.getId()), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ErrorMessage("No user with this username found!"), HttpStatus.NOT_FOUND);
    }

}
