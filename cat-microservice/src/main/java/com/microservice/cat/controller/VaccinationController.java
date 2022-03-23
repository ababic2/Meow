package com.microservice.cat.controller;

import com.microservice.cat.entity.Vaccination;
import com.microservice.cat.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class VaccinationController {
    @Autowired
    VaccinationRepository vaccinationRepository;

    @GetMapping("/vaccinations") // done
    public List<Vaccination> getVaccinations() {
        return vaccinationRepository.findAll();
    }

    @GetMapping("/vaccinations/{id}") // done
    public Vaccination getVaccination(@PathVariable Long id) {
        return vaccinationRepository.findById(id).get();
    }

    @PostMapping("/vaccinations") // done
    public Vaccination createVaccination(@RequestBody Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    @DeleteMapping("/vaccinations/{id}") // done
    public void deleteVaccination(@PathVariable Long id) {
        vaccinationRepository.deleteById(id);
    }

    @PutMapping("/vaccinations/{id}") // done
    public void updateVacination(@PathVariable Long id ,@RequestBody Vaccination vaccination) {
        Vaccination updateVaccination = vaccinationRepository.getById(id);
        updateVaccination.setVaccine(vaccination.getVaccine());
        updateVaccination.setDate(vaccination.getDate());
        vaccinationRepository.save(updateVaccination);

    }
}
