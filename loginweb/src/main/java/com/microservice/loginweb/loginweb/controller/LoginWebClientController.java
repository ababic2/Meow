package com.microservice.loginweb.loginweb.controller;

import com.microservice.loginweb.loginweb.exceptions.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginWebClientController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public LoginResponse handleRequest(Model model) {
        //accessing hello-service
        LoginResponse loginResponse = restTemplate.getForObject("http://login-microservice/api/user/1", LoginResponse.class);
        System.out.println(loginResponse);
        return loginResponse;
    }
}