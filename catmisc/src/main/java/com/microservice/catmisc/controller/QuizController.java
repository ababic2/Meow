package com.microservice.catmisc.controller;

import com.microservice.catmisc.entity.Quiz;
import com.microservice.catmisc.exceptions.CatMiscResponse;
import com.microservice.catmisc.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catmisc")
public class QuizController {

    @Autowired
    QuizRepository quizRepository;

    @GetMapping("/quiz") // done
    public List<Quiz> getQuizs() {
        return quizRepository.findAll();
    }

    @GetMapping("/quiz/{id}") // done
    public CatMiscResponse getQuizs(@PathVariable Long id) {
        try {
            Quiz findQuiz = quizRepository.findById(id).get();
            return CatMiscResponse.ok().setPayload(findQuiz);
        }catch (Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("Quiz with id "+id+" was not found", e);
        }
    }


    @PostMapping("/quiz") // done
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @DeleteMapping("/quiz/{id}") // done
    public CatMiscResponse deleteQuiz(@PathVariable Long id) {
        try {
            quizRepository.deleteById(id);
            return CatMiscResponse.ok().setMetadata("Quiz deleted");
        }catch(Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("Error deleting user ",e);
        }
    }

    @PutMapping("/quiz/{id}") // done
    public void updateQuiz(@PathVariable Long id ,@Valid @RequestBody Quiz quiz) {

    }

}