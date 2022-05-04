package com.microservice.chat.controller;

import com.microservice.chat.entity.Room;
import com.microservice.chat.entity.User;
import com.microservice.chat.exceptions.ChatResponse;
import com.microservice.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
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
            return ChatResponse.notFound().addErrorMsgToResponse("User with id "+id+" was not found", e);
        }
    }

    @PostMapping("/user")
    ChatResponse createUser(@Valid @RequestBody User user) {
        try {
            User createUser =  userRepository.save(user);
            return ChatResponse.ok().setPayload(createUser);
        }catch(Exception e){
            return ChatResponse.badRequest().addErrorMsgToResponse("Error creating user ",e);
        }
    }

    @DeleteMapping("/user/{id}") // done
    public ChatResponse deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return ChatResponse.ok().setMetadata(String.format("User deleted"));
        }catch(Exception e){
            return ChatResponse.notFound().addErrorMsgToResponse("Error deleting user ",e);
        }
    }

    @PutMapping("/user/{id}")
    public ChatResponse updateUser(@Valid @RequestBody User user, @PathVariable Long id) {
        User newUser = userRepository.findById(id).get();
        newUser.setUsername(user.getUsername());
        try{
            userRepository.save(newUser);
            return ChatResponse.ok().setPayload(newUser);
        }catch (Exception e){
            return ChatResponse.ok().addErrorMsgToResponse("User not updated: ",e);
        }
    }


}