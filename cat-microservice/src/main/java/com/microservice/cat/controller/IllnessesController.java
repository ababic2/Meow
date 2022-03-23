package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.entity.Health;
import com.microservice.cat.entity.Illnesses;
import com.microservice.cat.repository.HealthRepository;
import com.microservice.cat.repository.IllnessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class IllnessesController {

    @Autowired
    IllnessRepository illnessRepository;

    @GetMapping("/illnesses") // done
    public List<Illnesses> getIllnesses() {
        return illnessRepository.findAll();
    }

    @GetMapping("/illnesses/{id}") // done
    public Illnesses getIllness(@PathVariable Long id) {
        return illnessRepository.findById(id).get();
    }

    @PostMapping("/illnesses") // done
    public Illnesses createIllness(@RequestBody Illnesses illnesses) {
        return illnessRepository.save(illnesses);
    }

    @DeleteMapping("/illnesses/{id}") // done
    public void deleteIllness(@PathVariable Long id) {
        illnessRepository.deleteById(id);
    }

    @PutMapping("/illnesses/{id}") // done
    public void updateIllness(@PathVariable Long id ,@RequestBody Illnesses illnesses) {
        Illnesses updateIllness = illnessRepository.getById(id);
        updateIllness.setIllness(illnesses.getIllness());
        updateIllness.setDate(illnesses.getDate());
        updateIllness.setTherapy(illnesses.getTherapy());
        illnessRepository.save(updateIllness);
    }

}
