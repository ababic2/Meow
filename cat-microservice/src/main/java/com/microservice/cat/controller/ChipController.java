package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.entity.Chip;
import com.microservice.cat.repository.CatRepository;
import com.microservice.cat.repository.ChipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Chip getChip(@PathVariable Long id) {
        return chipRepository.findById(id).get();
    }

    @PostMapping("/chip") // done
    public Chip createChip(@RequestBody Chip chip) {
        return chipRepository.save(chip);
    }

    @DeleteMapping("/chip/{id}") // done
    public void deleteChip(@PathVariable Long id) {
        chipRepository.deleteById(id);
    }

    @PutMapping("/chip/{id}") // done
    public void updateChip(@PathVariable Long id ,@RequestBody Cat cat) {
        //Since chip currently only holds an id no need for this
    }

}
