package com.microservice.catmisc.controller;

import com.microservice.catmisc.entity.Vet;
import com.microservice.catmisc.exceptions.CatMiscResponse;
import com.microservice.catmisc.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catmisc")
public class VetController {

    @Autowired
    VetRepository vetRepository;

    @GetMapping("/vets") // done
    public List<Vet> getVets() {
        return vetRepository.findAll();
    }

    @GetMapping("/vets/{id}") // done
    public CatMiscResponse getVets(@PathVariable Long id) {
        try {
            Vet findVet = vetRepository.findById(id).get();
            return CatMiscResponse.ok().setPayload(findVet);
        }catch (Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("Vet with id "+id+" was not found", e);
        }
    }


    @PostMapping("/vets") // done
    public Vet createVet(@Valid @RequestBody Vet vet) {
        return vetRepository.save(vet);
    }

    @DeleteMapping("/vets/{id}") // done
    public CatMiscResponse deleteVet(@PathVariable Long id) {
        try {
            vetRepository.deleteById(id);
            return CatMiscResponse.ok().setMetadata("Vet deleted");
        }catch(Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("Error deleting vet ",e);
        }
    }

    @PutMapping("/vets/{id}") // done
    public CatMiscResponse updateVet(@PathVariable Long id ,@Valid @RequestBody Vet vet) {

        Vet updateVet = vetRepository.findById(id).get();

        updateVet.setGeoLocation(vet.getGeoLocation());
        updateVet.setName(vet.getName());

        try{
            vetRepository.save(updateVet);
            return CatMiscResponse.ok().setPayload(updateVet);
        }catch (Exception e){
            return CatMiscResponse.ok().addErrorMsgToResponse("Vet not updated: ",e);
        }

    }

}
