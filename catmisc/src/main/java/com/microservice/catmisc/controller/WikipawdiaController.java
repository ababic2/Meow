package com.microservice.catmisc.controller;

import com.microservice.catmisc.entity.Wikipawdia;
import com.microservice.catmisc.exceptions.CatMiscResponse;
import com.microservice.catmisc.repository.WikipawdiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catmisc")
public class WikipawdiaController {

    @Autowired
    WikipawdiaRepository wikipawdiaRepository;

    @GetMapping("/wikipawdia") // done
    public List<Wikipawdia> getWikipawdias() {
        return wikipawdiaRepository.findAll();
    }

    @GetMapping("/wikipawdia/{id}") // done
    public CatMiscResponse getWikipawdias(@PathVariable Long id) {
        try {
            Wikipawdia findWikipawdia = wikipawdiaRepository.findById(id).get();
            return CatMiscResponse.ok().setPayload(findWikipawdia);
        } catch (Exception e) {
            return CatMiscResponse.notFound().addErrorMsgToResponse("Wikipawdia with id " + id + " was not found", e);
        }
    }


    @PostMapping("/wikipawdia") // done
    public Wikipawdia createWikipawdia(@Valid @RequestBody Wikipawdia wikipawdia) {
        return wikipawdiaRepository.save(wikipawdia);
    }

    @DeleteMapping("/wikipawdia/{id}") // done
    public CatMiscResponse deleteWikipawdia(@PathVariable Long id) {
        try {
            wikipawdiaRepository.deleteById(id);
            return CatMiscResponse.ok().setMetadata("Wikipawdia deleted");
        } catch (Exception e) {
            return CatMiscResponse.notFound().addErrorMsgToResponse("Error deleting wikipawdia ", e);
        }
    }

    @PutMapping("/wikipawdia/{id}") // done
    public void updateWikipawdia(@PathVariable Long id, @Valid @RequestBody Wikipawdia wikipawdia) {

    }

}