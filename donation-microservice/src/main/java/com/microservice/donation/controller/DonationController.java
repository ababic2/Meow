package com.microservice.donation.controller;

import com.microservice.donation.entity.Donation;
import com.microservice.donation.entity.User;
import com.microservice.donation.exceptions.DonationResponse;
import com.microservice.donation.repository.DonationRepository;
import com.microservice.donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/donations") // done
    List<Donation> getDonations() {
        return donationRepository.findAll();
    }

    @GetMapping("/donations/{id}") // done
    public DonationResponse getDonation(@PathVariable Long id) {
        try {
            User findUser = userRepository.findById(id).get();
            return DonationResponse.ok().setPayload(findUser);
        }catch (Exception e){
            return DonationResponse.notFound().addErrorMsgToResponse("Donation with id "+id+" was not found", e);
        }
    }

    @PostMapping("/donations")
    DonationResponse createDonation(@Valid @RequestBody Donation donation) {
        try {
            Donation createDonation = donationRepository.save(donation);
            return DonationResponse.ok().setPayload(createDonation);
        }catch (Exception e){
            return DonationResponse.badRequest().addErrorMsgToResponse("Error creating donation ",e);
        }
    }

    @DeleteMapping("/donations/{id}") // done
    public DonationResponse deleteUser(@PathVariable Long id) {
        try {
            donationRepository.deleteById(id);
            return DonationResponse.ok().setMetadata(String.format("Donation deleted"));
        }catch(Exception e){
            return DonationResponse.notFound().addErrorMsgToResponse("Error deleting donation ",e);
        }
    }

    @PutMapping("update/{id}")
    public DonationResponse updateUser(@Valid @RequestBody Donation donation, @PathVariable Long id) {
        Donation updDon = donationRepository.findById(id).get();
        if(updDon == null) return null;

        updDon.setAmount(donation.getAmount());
        updDon.setDate(donation.getDate());
        updDon.setType(donation.getType());

        try{
            donationRepository.save(updDon);
            return DonationResponse.ok().setPayload(updDon);
        }catch (Exception e){
            return DonationResponse.ok().addErrorMsgToResponse("User not updated: ",e);
        }
    }
}
