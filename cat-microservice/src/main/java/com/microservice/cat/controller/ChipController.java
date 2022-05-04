package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.entity.Chip;
import com.microservice.cat.exceptions.CatResponse;
import com.microservice.cat.repository.CatRepository;
import com.microservice.cat.repository.ChipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cat")
public class ChipController {

    @Autowired
    ChipRepository chipRepository;

    @GetMapping("/chip") // done
    public List<Chip> getChips() {
        return chipRepository.findAll();
    }

    @GetMapping("/chip/{id}") // done
    public CatResponse getChip(@PathVariable Long id) {
        try{
            Chip findChip = chipRepository.findById(id).get();
            return CatResponse.ok().setPayload(findChip);
        }catch (Exception e){
            return CatResponse.notFound().addErrorMsgToResponse("Chip with id "+id+" was not found", e);
        }
    }

    @PostMapping("/chip") // done
    public CatResponse createChip(@Valid @RequestBody Chip chip) {
        try{
            Chip createChip = chipRepository.save(chip);
            return CatResponse.ok().setPayload(createChip);
        }catch (Exception e){
            return CatResponse.badRequest().addErrorMsgToResponse("Error creating chip", e);
        }
    }

    @DeleteMapping("/chip/{id}") // done
    public CatResponse deleteChip(@PathVariable Long id) {
        try{
            chipRepository.deleteById(id);
            return CatResponse.ok().setMetadata(String.format("Chip deleted"));
        }catch (Exception e){
            return CatResponse.notFound().addErrorMsgToResponse("Error deleting chip", e);
        }

    }

    @PutMapping("/chip/{id}") // done
    public void updateChip(@PathVariable Long id ,@Valid @RequestBody Cat cat) {
        //Since chip currently only holds an id no need for this
    }

}
