package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.entity.Cat;
import com.microservice.cat.exceptions.CatResponse;
import com.microservice.cat.repository.AccountRepository;
import com.microservice.cat.repository.CatRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public CatResponse getCat(@PathVariable Long id) {
        try {
            Cat findCat = catRepository.findById(id).get();
            return CatResponse.ok().setPayload(findCat);
        }catch (Exception e){
            return CatResponse.notFound().addErrorMsgToResponse("Cat with id "+id+" was not found", e);
        }
    }

    @PostMapping("/") // done
    public CatResponse createCat(@Valid @RequestBody Cat cat) {
        try {
            Cat createCat = catRepository.save(cat);
            return CatResponse.ok().setPayload(createCat);
        }catch(Exception e){
            return CatResponse.badRequest().addErrorMsgToResponse("Error creating cat ",e);
        }
    }

    @DeleteMapping("/{id}") // done
    public CatResponse deleteCat(@PathVariable Long id) {
        try {
            catRepository.deleteById(id);
            return CatResponse.ok().setMetadata(String.format("Cat deleted"));
        }catch(Exception e){
            return CatResponse.notFound().addErrorMsgToResponse("Error deleting cat ", e);
        }
    }

    @PutMapping("/{id}") // done
    public CatResponse updateCat(@PathVariable Long id ,@Valid  @RequestBody Cat cat) {
        Cat updateCat = catRepository.getById(id);
        updateCat.setAccount(cat.getAccount());
        updateCat.setDescription(cat.getDescription());
        updateCat.setHealth(cat.getHealth());
        try{
            catRepository.save(updateCat);
            return CatResponse.ok().setPayload(updateCat);
        }catch (Exception e){
            return CatResponse.ok().addErrorMsgToResponse("Cat not updated: ", e);
        }

    }

}
