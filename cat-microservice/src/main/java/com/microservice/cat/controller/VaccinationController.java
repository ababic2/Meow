package com.microservice.cat.controller;

import com.microservice.cat.entity.Illnesses;
import com.microservice.cat.entity.Vaccination;
import com.microservice.cat.exceptions.CatResponse;
import com.microservice.cat.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cat")
public class VaccinationController {
    @Autowired
    VaccinationRepository vaccinationRepository;

    @GetMapping("/vaccinations") // done
    public List<Vaccination> getVaccinations() {
        return vaccinationRepository.findAll();
    }

    @GetMapping("/vaccinations/{id}") // done
    public CatResponse getVaccination(@PathVariable Long id) {
        try{
            Vaccination findVaccination = vaccinationRepository.findById(id).get();
            return CatResponse.ok().setPayload(findVaccination);
        }catch (Exception e){
            return CatResponse.notFound().setErrors(String.format("Vaccination with id "+id+" was not found"));
        }
    }

    @PostMapping("/vaccinations") // done
    public CatResponse createVaccination(@RequestBody Vaccination vaccination) {
        try{
            Vaccination createVaccination = vaccinationRepository.save(vaccination);
            return CatResponse.ok().setPayload(createVaccination);
        }catch (Exception e){
            return CatResponse.badRequest().setErrors(String.format("Error creating vaccination "+e.getMessage()));
        }
    }

    @DeleteMapping("/vaccinations/{id}") // done
    public CatResponse deleteVaccination(@PathVariable Long id) {
        try{
            vaccinationRepository.deleteById(id);
            return CatResponse.ok().setMetadata("Vaccination deleted");
        }catch (Exception e){
            return CatResponse.notFound().setErrors(String.format("Error deleting vaccination "+e.getMessage()));
        }

    }

    @PutMapping("/vaccinations/{id}") // done
    public CatResponse updateVacination(@PathVariable Long id ,@RequestBody Vaccination vaccination) {
        Vaccination updateVaccination = vaccinationRepository.getById(id);
        updateVaccination.setVaccine(vaccination.getVaccine());
        updateVaccination.setDate(vaccination.getDate());
        try{
            vaccinationRepository.save(updateVaccination);
            return CatResponse.ok().setPayload(updateVaccination);
        }catch (Exception e){
            return CatResponse.ok().setErrors(String.format("Vaccination not updated: "+e.getMessage()));
        }
    }
}
