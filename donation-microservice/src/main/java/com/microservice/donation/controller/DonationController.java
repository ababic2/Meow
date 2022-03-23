package com.microservice.donation.controller;

import com.microservice.donation.entity.Donation;
import com.microservice.donation.entity.User;
import com.microservice.donation.repository.DonationRepository;
import com.microservice.donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DonationController {

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/donations") // done
    List<Donation> getDonations() {
        return donationRepository.findAll();
    }

    @PostMapping("/donate")
    Donation createDonation(@RequestBody Donation donation) {
        return donationRepository.save(donation);
    }
}
