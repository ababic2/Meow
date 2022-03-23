package com.microservice.donation.controller;

import com.microservice.donation.entity.Donation;
import com.microservice.donation.entity.User;
import com.microservice.donation.repository.DonationRepository;
import com.microservice.donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donation")
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

    @DeleteMapping("/delete/{id}") // done
    public Donation deleteUser(@PathVariable Long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        if(donation.isPresent()) donationRepository.deleteById(id);
        return donation.get();
    }

    @PutMapping("update/{id}")
    public Donation updateUser(@RequestBody Donation donation, @PathVariable Long id) {
        Donation updDon = donationRepository.findById(id).get();
        if(updDon == null) return null;

        updDon.setAmount(donation.getAmount());
        updDon.setDate(donation.getDate());
        updDon.setType(donation.getType());
        //updDon.setUser(donation.getUser());
        donationRepository.save(updDon);

        return updDon;
    }
}
