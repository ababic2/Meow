package com.microservice.login.controller;

import com.microservice.login.entity.User;
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

    @GetMapping("/user/{id}") // done
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/create")
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/delete/{id}") // done
    public void deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) userRepository.deleteById(id);
    }

    @PutMapping("update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        User newUser = userRepository.findById(id).get();
        if(newUser == null) return null;

        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setCat(user.isCat());
        userRepository.save(newUser);

        return newUser;
    }
}