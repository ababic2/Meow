package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.entity.Health;
import com.microservice.cat.entity.Illnesses;
import com.microservice.cat.exceptions.CatResponse;
import com.microservice.cat.repository.HealthRepository;
import com.microservice.cat.repository.IllnessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cat")
public class IllnessesController {

    @Autowired
    IllnessRepository illnessRepository;

    @GetMapping("/illness") // done
    public List<Illnesses> getIllnesses() {
        return illnessRepository.findAll();
    }

    @GetMapping("/illness/{id}") // done
    public CatResponse getIllness(@PathVariable Long id) {
        try{
            Illnesses findIllnesses = illnessRepository.findById(id).get();
            return CatResponse.ok().setPayload(findIllnesses);
        }catch (Exception e){
            return CatResponse.notFound().setErrors(String.format("Illness with id "+id+" was not found"));
        }
    }

    @PostMapping("/illness") // done
    public CatResponse createIllness(@RequestBody Illnesses illnesses) {
        try{
            Illnesses createIllnesse = illnessRepository.save(illnesses);
            return CatResponse.ok().setPayload(createIllnesse);
        }catch (Exception e){
            return CatResponse.badRequest().setErrors(String.format("Error creating illness "+e.getMessage()));
        }
    }

    @DeleteMapping("/illness/{id}") // done
    public CatResponse deleteIllness(@PathVariable Long id) {
        try{
            illnessRepository.deleteById(id);
            return CatResponse.ok().setMetadata("Illness deleted");
        }catch (Exception e){
            return CatResponse.notFound().setErrors(String.format("Error deleting Illness "+e.getMessage()));
        }

    }

    @PutMapping("/illness/{id}") // done
    public CatResponse updateIllness(@PathVariable Long id ,@RequestBody Illnesses illnesses) {
        Illnesses updateIllness = illnessRepository.getById(id);
        updateIllness.setIllness(illnesses.getIllness());
        updateIllness.setDate(illnesses.getDate());
        updateIllness.setTherapy(illnesses.getTherapy());
        try{
            illnessRepository.save(updateIllness);
            return CatResponse.ok().setPayload(updateIllness);
        }catch (Exception e){
            return CatResponse.ok().setErrors(String.format("Illness not updated: "+e.getMessage()));
        }

    }

}
