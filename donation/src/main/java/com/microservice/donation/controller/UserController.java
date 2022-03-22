package com.microservice.donation.controller;

import com.microservice.donation.entity.User;
import com.microservice.donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users") // done
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}