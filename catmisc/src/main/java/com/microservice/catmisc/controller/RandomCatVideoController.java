package com.microservice.catmisc.controller;

import com.microservice.catmisc.entity.CatVideo;
import com.microservice.catmisc.exceptions.CatMiscResponse;
import com.microservice.catmisc.repository.CatVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catmisc")
public class RandomCatVideoController {

    @Autowired
    CatVideoRepository catVideoRepository;

    @GetMapping("/catvideos") // done
    public List<CatVideo> getCatVideos() {
        return catVideoRepository.findAll();
    }

    @GetMapping("/catvideos/{id}") // done
    public CatMiscResponse getCatVideos(@PathVariable Long id) {
        try {
            CatVideo findCatVideo = catVideoRepository.findById(id).get();
            return CatMiscResponse.ok().setPayload(findCatVideo);
        } catch (Exception e) {
            return CatMiscResponse.notFound().addErrorMsgToResponse("CatVideo with id " + id + " was not found", e);
        }
    }


    @PostMapping("/catvideos") // done
    public CatVideo createCatVideo(@Valid @RequestBody CatVideo catVideo) {
        return catVideoRepository.save(catVideo);
    }

    @DeleteMapping("/catvideos/{id}") // done
    public CatMiscResponse deleteCatVideo(@PathVariable Long id) {
        try {
            catVideoRepository.deleteById(id);
            return CatMiscResponse.ok().setMetadata("CatVideo deleted");
        } catch (Exception e) {
            return CatMiscResponse.notFound().addErrorMsgToResponse("Error deleting video ", e);
        }
    }

    @PutMapping("/catvideos/{id}") // done
    public CatMiscResponse updateCatVideo(@PathVariable Long id, @Valid @RequestBody CatVideo catVideo) {

        CatVideo updateCatVideo = catVideoRepository.findById(id).get();

        updateCatVideo.setUrl(catVideo.getUrl());

        try {
            catVideoRepository.save(updateCatVideo);
            return CatMiscResponse.ok().setPayload(updateCatVideo);
        } catch (Exception e) {
            return CatMiscResponse.ok().addErrorMsgToResponse("CatVideo not updated: ", e);
        }

    }

}