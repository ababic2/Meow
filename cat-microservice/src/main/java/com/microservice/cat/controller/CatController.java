package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.repository.AccountRepository;
import com.microservice.cat.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    CatRepository catRepository;

    @GetMapping("/") // done
    public List<Cat> getCats() {
        return catRepository.findAll();
    }

    @GetMapping("/{id}") // done
    public Cat getCat(@PathVariable Long id) {
        return catRepository.findById(id).get();
    }

    @PostMapping("/") // done
    public Cat createCat(@RequestBody Cat cat) {
        return catRepository.save(cat);
    }

    @DeleteMapping("/{id}") // done
    public void deleteCat(@PathVariable Long id) {
        catRepository.deleteById(id);
    }

    @PutMapping("/{id}") // done
    public void updateCat(@PathVariable Long id ,@RequestBody Cat cat) {
        Cat updateCat = catRepository.getById(id);
        updateCat.setAccount(cat.getAccount());
        updateCat.setDescription(cat.getDescription());
        updateCat.setHealth(cat.getHealth());
        catRepository.save(updateCat);
    }

}
