package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.entity.Health;
import com.microservice.cat.repository.CatRepository;
import com.microservice.cat.repository.HealthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class HealthController {

    @Autowired
    HealthRepository healthRepository;

    @GetMapping("/health") // done
    public List<Health> getHealths() {
        return healthRepository.findAll();
    }

    @GetMapping("/health/{id}") // done
    public Health getHealth(@PathVariable Long id) {
        return healthRepository.findById(id).get();
    }

    @PostMapping("/health") // done
    public Health createHealth(@RequestBody Health health) {
        return healthRepository.save(health);
    }

    @DeleteMapping("/health/{id}") // done
    public void deleteHealth(@PathVariable Long id) {
        healthRepository.deleteById(id);
    }

    @PutMapping("/health/{id}") // done
    public void updateHealth(@PathVariable Long id ,@RequestBody Health health) {
        //Since health currently only holds an id no need for this
    }

}
