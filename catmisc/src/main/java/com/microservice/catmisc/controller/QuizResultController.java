package com.microservice.catmisc.controller;

import com.microservice.catmisc.entity.QuizAnswer;
import com.microservice.catmisc.entity.QuizResult;
import com.microservice.catmisc.exceptions.CatMiscResponse;
import com.microservice.catmisc.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catmisc")
public class QuizResultController {

    @Autowired
    QuizResultRepository quizResultRepository;

    @GetMapping("/quizresults") // done
    public List<QuizResult> getQuizResults() {
        return quizResultRepository.findAll();
    }

    @GetMapping("/quizresults/{id}") // done
    public CatMiscResponse getQuizResults(@PathVariable Long id) {
        try {
            QuizResult findQuizResult = quizResultRepository.findById(id).get();
            return CatMiscResponse.ok().setPayload(findQuizResult);
        }catch (Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("QuizResult with id "+id+" was not found", e);
        }
    }

    @PostMapping("/quizresult") // done
    public QuizResult createQuizResult(@Valid @RequestBody QuizResult quizResult) {
        return quizResultRepository.save(quizResult);
    }

    @DeleteMapping("/quizresult/{id}") // done
    public CatMiscResponse deleteQuizResult(@PathVariable Long id) {
        try {
            quizResultRepository.deleteById(id);
            return CatMiscResponse.ok().setMetadata("QuizResult deleted");
        }catch(Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("Error deleting user ",e);
        }
    }

    @PutMapping("/quizresult/{id}") // done
    public CatMiscResponse updateQuizResult(@PathVariable Long id ,@Valid @RequestBody QuizResult quizResult) {


        QuizResult updateQuizResponse = quizResultRepository.findById(id).get();

        updateQuizResponse.setRangeLow(quizResult.getRangeLow());
        updateQuizResponse.setRangeHigh(quizResult.getRangeHigh());
        updateQuizResponse.setResult(quizResult.getResult());

        try{
            quizResultRepository.save(updateQuizResponse);
            return CatMiscResponse.ok().setPayload(updateQuizResponse);
        }catch (Exception e){
            return CatMiscResponse.ok().addErrorMsgToResponse("QuizResult not updated: ",e);
        }

    }

}