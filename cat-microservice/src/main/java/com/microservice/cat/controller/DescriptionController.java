package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.entity.Chip;
import com.microservice.cat.entity.Description;
import com.microservice.cat.repository.ChipRepository;
import com.microservice.cat.repository.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class DescriptionController {

    @Autowired
    DescriptionRepository descriptionRepository;

    @GetMapping("/descriptions") // done
    public List<Description> getDescriptions() {
        return descriptionRepository.findAll();
    }

    @GetMapping("/descriptions/{id}") // done
    public Description getDescription(@PathVariable Long id) {
        return descriptionRepository.findById(id).get();
    }

    @PostMapping("/descriptions") // done
    public Description createDescription(@RequestBody Description description) {
        return descriptionRepository.save(description);
    }

    @DeleteMapping("/descriptions/{id}") // done
    public void deleteDescription(@PathVariable Long id) {
        descriptionRepository.deleteById(id);
    }

    @PutMapping("/descriptions/{id}") // done
    public void updateDescription(@PathVariable Long id ,@RequestBody Description description) {
        Description updateDescription = descriptionRepository.getById(id);
        updateDescription.setName(description.getName());
        updateDescription.setGender(description.getGender());
        updateDescription.setAge(description.getAge());
        updateDescription.setChip(description.getChip());
        updateDescription.setSpecies(description.getSpecies());

        descriptionRepository.save(updateDescription);
    }

}
