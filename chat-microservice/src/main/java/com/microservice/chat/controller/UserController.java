package com.microservice.chat.controller;

import com.microservice.chat.entity.Room;
import com.microservice.chat.entity.User;
import com.microservice.chat.exceptions.ChatResponse;
import com.microservice.chat.repository.UserRepository;
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
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}") // done
    public ChatResponse getUser(@PathVariable Long id) {
        try {
            User findUser = userRepository.findById(id).get();
            return ChatResponse.ok().setPayload(findUser);
        }catch (Exception e){
            return ChatResponse.notFound().setErrors(String.format("User with id "+id+" was not found"));
        }
    }

    @PostMapping("/create")
    ChatResponse createUser(@RequestBody User user) {
        try {
            User createUser =  userRepository.save(user);
            return ChatResponse.ok().setPayload(createUser);
        }catch(Exception e){
            return ChatResponse.badRequest().setErrors(String.format("Error creating user "+e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}") // done
    public ChatResponse deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return ChatResponse.ok().setMetadata(String.format("User deleted"));
        }catch(Exception e){
            return ChatResponse.notFound().setErrors(String.format("Error deleting user "+e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ChatResponse updateUser(@RequestBody User user, @PathVariable Long id) {
        User newUser = userRepository.findById(id).get();
        newUser.setUsername(user.getUsername());
        try{
            userRepository.save(newUser);
            return ChatResponse.ok().setPayload(newUser);
        }catch (Exception e){
            return ChatResponse.ok().setErrors(String.format("User not updated: "+e.getMessage()));
        }
    }


}