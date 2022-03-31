package com.microservice.donation.controller;

import com.microservice.donation.entity.Donation;
import com.microservice.donation.entity.User;
import com.microservice.donation.exceptions.DonationResponse;
import com.microservice.donation.repository.DonationRepository;
import com.microservice.donation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/") // done
    List<Donation> getDonations() {
        return donationRepository.findAll();
    }

    @GetMapping("/{id}") // done
    public DonationResponse getDonation(@PathVariable Long id) {
        try {
            Donation findDonation = donationRepository.findById(id).get();
            return DonationResponse.ok().setPayload(findDonation);
        }catch (Exception e){
            return DonationResponse.notFound().setErrors(String.format("Donation with id "+id+" was not found"));
        }
    }

    @GetMapping("/top3")
    public DonationResponse getTop3Donations(){
        try{
            List<Donation> top = donationRepository.findTop3ByOrderByAmountDesc();
            return DonationResponse.ok().setPayload(top);
        }catch (Exception e){
            return DonationResponse.notFound().setErrors(String.format("Couldn't find top 3 donations"));
        }
    }
    @GetMapping("/top")
    public DonationResponse getTopDonation(){
        try{
            Donation top = donationRepository.findTopByOrderByAmountDesc();
            return DonationResponse.ok().setPayload(top);
        }catch (Exception e){
            return DonationResponse.notFound().setErrors(String.format("Couldn't find top donations"));
        }
    }

    @GetMapping("/top/{page}")
    public DonationResponse getTopDonationsPage(@PathVariable String page){
        try{
            List<Donation> top = donationRepository.findTopUserDefined(PageRequest.of(Integer.parseInt(page),5));
            return DonationResponse.ok().setPayload(top);
        }catch (Exception e){
            return DonationResponse.notFound().setErrors(String.format("Couldn't find page of top donations"));
        }
    }

    @GetMapping("/byuser/{id}")
    public DonationResponse getDonationsByUser(@PathVariable Long id){
        try{
            List<Donation> donations = donationRepository.findByUserId(userRepository.findById(id).get().getId());
            return DonationResponse.ok().setPayload(donations);
        }catch (Exception e){
            System.out.println(e);
            return DonationResponse.notFound().setErrors(String.format("Couldn't find top 3 donations"));
        }
    }

    @PostMapping("/")
    DonationResponse createDonation(@RequestBody Donation donation) {
        try {
            Donation createDonation = donationRepository.save(donation);
            return DonationResponse.ok().setPayload(createDonation);
        }catch (Exception e){
            return DonationResponse.badRequest().setErrors(String.format("Error creating donation "+e.getMessage()));
        }
    }

    @DeleteMapping("/{id}") // done
    public DonationResponse deleteUser(@PathVariable Long id) {
        try {
            donationRepository.deleteById(id);
            return DonationResponse.ok().setMetadata(String.format("Donation deleted"));
        }catch(Exception e){
            return DonationResponse.notFound().setErrors(String.format("Error deleting donation "+e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public DonationResponse updateUser(@RequestBody Donation donation, @PathVariable Long id) {
        Donation updDon = donationRepository.findById(id).get();
        if(updDon == null) return null;

        updDon.setAmount(donation.getAmount());
        updDon.setDate(donation.getDate());
        updDon.setType(donation.getType());

        try{
            donationRepository.save(updDon);
            return DonationResponse.ok().setPayload(updDon);
        }catch (Exception e){
            return DonationResponse.ok().setErrors(String.format("User not updated: "+e.getMessage()));
        }
    }
}
