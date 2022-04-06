package com.microservice.catmisc.controller;

import com.microservice.catmisc.entity.QuizQuestion;
import com.microservice.catmisc.exceptions.CatMiscResponse;
import com.microservice.catmisc.repository.QuizQuestionRepository;
import com.microservice.catmisc.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/catmisc")
public class QuizQuestionController {

    @Autowired
    QuizQuestionRepository quizQuestionRepository;

    @GetMapping("/quizQuestions") // done
    public List<QuizQuestion> getQuizQuestions() {
        return quizQuestionRepository.findAll();
    }

    @GetMapping("/quizQuestions/{id}") // done
    public CatMiscResponse getQuizQuestions(@PathVariable Long id) {
        try {
            QuizQuestion findQuizQuestion = quizQuestionRepository.findById(id).get();
            return CatMiscResponse.ok().setPayload(findQuizQuestion);
        }catch (Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("QuizQuestion with id "+id+" was not found", e);
        }
    }


    @PostMapping("/quizQuestions") // done
    public QuizQuestion createQuizQuestion(@Valid @RequestBody QuizQuestion quizQuestion) {
        return quizQuestionRepository.save(quizQuestion);
    }

    @DeleteMapping("/quizQuestions/{id}") // done
    public CatMiscResponse deleteQuizQuestion(@PathVariable Long id) {
        try {
            quizQuestionRepository.deleteById(id);
            return CatMiscResponse.ok().setMetadata("QuizQuestion deleted");
        }catch(Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("Error deleting quizQuestion ",e);
        }
    }

    @PutMapping("/quizQuestions/{id}") // done
    public CatMiscResponse updateQuizQuestion(@PathVariable Long id ,@Valid @RequestBody QuizQuestion quizQuestion) {

        QuizQuestion updateQuizQuestion = quizQuestionRepository.findById(id).get();

        updateQuizQuestion.setQuizAnswers(quizQuestion.getQuizAnswers());

        try{
            quizQuestionRepository.save(updateQuizQuestion);
            return CatMiscResponse.ok().setPayload(updateQuizQuestion);
        }catch (Exception e){
            return CatMiscResponse.ok().addErrorMsgToResponse("QuizQuestion not updated: ",e);
        }

    }

}
