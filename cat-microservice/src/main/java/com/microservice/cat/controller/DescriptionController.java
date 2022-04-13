package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.entity.Chip;
import com.microservice.cat.entity.Description;
import com.microservice.cat.exceptions.CatResponse;
import com.microservice.cat.repository.ChipRepository;
import com.microservice.cat.repository.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/cat")
public class DescriptionController {

    @Autowired
    DescriptionRepository descriptionRepository;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/descriptions") // done
    public CatResponse getDescriptions() {
        try{
            List<Description> findDescriptions = descriptionRepository.findAll();
            return CatResponse.ok().setPayload(findDescriptions);
        }catch (Exception e){
            return CatResponse.notFound().addErrorMsgToResponse("Descriptions error", e);
        }
    }

    @GetMapping("/descriptions/{id}") // done
    public CatResponse getDescription(@PathVariable Long id) {
        try{
            Description findDescription = descriptionRepository.findById(id).get();
            return CatResponse.ok().setPayload(findDescription);
        }catch (Exception e){
            return CatResponse.notFound().addErrorMsgToResponse("Description with id "+id+" was not found", e);
        }
    }

    @PostMapping("/descriptions") // done
    public CatResponse createDescription(@Valid @RequestBody Description description) {
        try{
            Description createDescription = descriptionRepository.save(description);
            return CatResponse.ok().setPayload(createDescription);
        }catch (Exception e){
            return CatResponse.badRequest().addErrorMsgToResponse("Error creating description ",e);
        }
    }

    @DeleteMapping("/descriptions/{id}") // done
    public CatResponse deleteDescription(@PathVariable Long id) {
        try{
            descriptionRepository.deleteById(id);
            return CatResponse.ok().setMetadata("Description deleted");
        }catch (Exception e){
            return CatResponse.notFound().addErrorMsgToResponse("Error deleting description ",e);
        }
    }

    @PutMapping("/descriptions/{id}") // done
    public CatResponse updateDescription(@PathVariable Long id ,@Valid @RequestBody Description description) {
        Description updateDescription = descriptionRepository.findById(id).get();

        updateDescription.setName(description.getName());
        updateDescription.setGender(description.getGender());
        updateDescription.setAge(description.getAge());
        updateDescription.setChip(description.getChip());
        updateDescription.setSpecies(description.getSpecies());

        try{
            descriptionRepository.save(updateDescription);
            return CatResponse.ok().setPayload(updateDescription);
        }catch (Exception e){
            return CatResponse.ok().addErrorMsgToResponse("Description not updated: ",e);
        }

    }

}
