package com.microservice.login.controller;

import com.microservice.login.entity.User;
import com.microservice.login.exceptions.LoginResponse;
import com.microservice.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users") // done
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}") // done
    public LoginResponse getUser(@PathVariable Long id) {
        try{
            User findUser = userRepository.findById(id).get();
            return LoginResponse.ok().setPayload(findUser);
        }catch (Exception e){
            return LoginResponse.notFound().setErrors(String.format("User with id "+id+" was not found"));
        }

    }

    @PostMapping("/user")
    LoginResponse createUser(@RequestBody User user) {
        try {
            User createUser =  userRepository.save(user);
            return LoginResponse.ok().setPayload(createUser);
        }catch(Exception e){
            return LoginResponse.badRequest().setErrors(String.format("Error creating user "+e.getMessage()));
        }
    }

    @DeleteMapping("/user/{id}") // done
    public LoginResponse deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return LoginResponse.ok().setMetadata(String.format("User deleted"));
        }catch(Exception e){
            return LoginResponse.notFound().setErrors(String.format("Error deleting user "+e.getMessage()));
        }
    }

    @PutMapping("/user/{id}")
    public LoginResponse updateUser(@RequestBody User user, @PathVariable Long id) {
        User newUser = userRepository.findById(id).get();

        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setCat(user.isCat());
        try{
            userRepository.save(newUser);
            return LoginResponse.ok().setPayload(newUser);
        }catch (Exception e){
            return LoginResponse.ok().setErrors(String.format("User not updated: "+e.getMessage()));
        }
    }
}