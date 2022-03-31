package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.entity.Description;
import com.microservice.cat.entity.Health;
import com.microservice.cat.exceptions.CatResponse;
import com.microservice.cat.repository.CatRepository;
import com.microservice.cat.repository.HealthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cat")
public class HealthController {

    @Autowired
    HealthRepository healthRepository;

    @GetMapping("/health") // done
    public List<Health> getHealths() {
        return healthRepository.findAll();
    }

    @GetMapping("/health/{id}") // done
    public CatResponse getHealth(@PathVariable Long id) {
        try{
            Health findHealth = healthRepository.findById(id).get();
            return CatResponse.ok().setPayload(findHealth);
        }catch (Exception e){
            return CatResponse.notFound().addErrorMsgToResponse("Health with id "+id+" was not found", e);
        }
    }

    @PostMapping("/health") // done
    public CatResponse createHealth(@Valid @RequestBody Health health) {
        try{
            Health createHealth = healthRepository.save(health);
            return CatResponse.ok().setPayload(createHealth);
        }catch (Exception e){
            return CatResponse.badRequest().addErrorMsgToResponse("Error creating health ",e);
        }
    }

    @DeleteMapping("/health/{id}") // done
    public CatResponse deleteHealth(@PathVariable Long id) {
        try{
            healthRepository.deleteById(id);
            return CatResponse.ok().setMetadata("Health deleted");
        }catch (Exception e){
            return CatResponse.notFound().addErrorMsgToResponse("Error deleting health ",e);
        }

    }

    @PutMapping("/health/{id}") // done
    public void updateHealth(@PathVariable Long id ,@Valid  @RequestBody Health health) {
        //Since health currently only holds an id no need for this
    }

}
