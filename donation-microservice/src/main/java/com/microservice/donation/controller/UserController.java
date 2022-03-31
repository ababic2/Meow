package com.microservice.donation.controller;

import com.microservice.donation.entity.Donation;
import com.microservice.donation.entity.User;
import com.microservice.donation.exceptions.DonationResponse;
import com.microservice.donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donation")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user") // done
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}") // done
    public DonationResponse getDonation(@PathVariable Long id) {
        try {
            User findDonation = userRepository.findById(id).get();
            return DonationResponse.ok().setPayload(findDonation);
        }catch (Exception e){
            return DonationResponse.notFound().setErrors(String.format("Donation with id "+id+" was not found"));
        }
    }

    @PostMapping("/user")
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/user/{id}") // done
    public void deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        User newUser = userRepository.findById(id).get();
        if(newUser == null) return null;

        newUser.setDonated_sum(user.getDonated_sum());
        userRepository.save(newUser);

        return newUser;
    }
}