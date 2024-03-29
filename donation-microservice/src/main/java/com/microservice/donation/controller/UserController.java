package com.microservice.donation.controller;

import com.microservice.donation.entity.User;
import com.microservice.donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/donation/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users") // done
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/delete/{id}") // done
    public void deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @PutMapping("update/{id}")
    public User updateUser(@Valid @RequestBody User user, @PathVariable Long id) {
        User newUser = userRepository.findById(id).get();
        if(newUser == null) return null;

        newUser.setDonated_sum(user.getDonated_sum());
        userRepository.save(newUser);

        return newUser;
    }
}