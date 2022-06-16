package com.microservice.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.login.config.JwtTokenUtil;
import com.microservice.login.entity.LoginRequest;
import com.microservice.login.entity.User;
import com.microservice.login.exceptions.LoginResponse;
import com.microservice.login.repository.UserRepository;
import com.microservice.login.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private final JwtTokenUtil jwtTokenUtil;
    private final LoginService loginService;


    public UserController(JwtTokenUtil jwtTokenUtil, LoginService loginService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.loginService = loginService;
    }

    @GetMapping("/users") // done
    public LoginResponse getUsers() {
        try{
            List<User> findUsers = userRepository.findAll();
            return LoginResponse.ok().setPayload(findUsers);
        }catch (Exception e){
            return LoginResponse.notFound().addErrorMsgToResponse("Error getting users", e);
        }

    }

    @PostMapping("/log")
    public ResponseEntity<?> loginAsClientOrInstructor(@RequestBody LoginRequest loginRequest) {
        System.out.println("***************************************");
        System.out.println(loginRequest.getUserName());
        return loginService.login(loginRequest);
    }

    @GetMapping("/whoAmI")
    public  ResponseEntity<?> whoAmI(@RequestHeader("Authorization") String token) throws JsonProcessingException, UnsupportedEncodingException {
        return new ResponseEntity<>(jwtTokenUtil.getTokenSubject(token), HttpStatus.OK);
    }

    @GetMapping("/user/{id}") // done
    public LoginResponse getUser(@PathVariable Long id) {
        try{
            User findUser = userRepository.findById(id).get();
            return LoginResponse.ok().setPayload(findUser);
        }catch (Exception e){
            return LoginResponse.notFound().addErrorMsgToResponse("User with id "+id+" was not found", e);
        }

    }

    @PostMapping("/create")
    LoginResponse createUser(@Valid @RequestBody User user) {
        try {
            User createUser =  userRepository.save(user);
            return LoginResponse.ok().setPayload(createUser);
        }catch(Exception e){
            return LoginResponse.badRequest().addErrorMsgToResponse("Error creating user ",e);
        }
    }

    @DeleteMapping("/delete/{id}") // done
    public LoginResponse deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return LoginResponse.ok().setMetadata(String.format("User deleted"));
        }catch(Exception e){
            return LoginResponse.notFound().addErrorMsgToResponse("Error deleting user ",e);
        }
    }

    @PutMapping("/users/{id}")
    public LoginResponse updateUser(@Valid @RequestBody User user, @PathVariable Long id) {
        User newUser = userRepository.findById(id).get();

        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setCat(user.isCat());
        try{
            userRepository.save(newUser);
            return LoginResponse.ok().setPayload(newUser);
        }catch (Exception e){
            return LoginResponse.ok().addErrorMsgToResponse("User not updated: ",e);
        }
    }
}