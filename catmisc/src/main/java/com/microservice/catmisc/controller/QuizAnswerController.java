package com.microservice.catmisc.controller;

import com.microservice.catmisc.entity.QuizAnswer;
import com.microservice.catmisc.exceptions.CatMiscResponse;
import com.microservice.catmisc.repository.QuizAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catmisc")
public class QuizAnswerController {

    @Autowired
    QuizAnswerRepository quizAnswerRepository;

    @GetMapping("/quizanswers") // done
    public List<QuizAnswer> getQuizAnswers() {
        return quizAnswerRepository.findAll();
    }

    @GetMapping("/quizanswers/{id}") // done
    public CatMiscResponse getQuizAnswers(@PathVariable Long id) {
        try {
            QuizAnswer findQuizAnswer = quizAnswerRepository.findById(id).get();
            return CatMiscResponse.ok().setPayload(findQuizAnswer);
        }catch (Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("QuizAnswer with id "+id+" was not found", e);
        }
    }


    @PostMapping("/quizanswers") // done
    public QuizAnswer createQuizAnswer(@Valid @RequestBody QuizAnswer quizAnswer) {
        return quizAnswerRepository.save(quizAnswer);
    }

    @DeleteMapping("/quizanswers/{id}") // done
    public CatMiscResponse deleteQuizAnswer(@PathVariable Long id) {
        try {
            quizAnswerRepository.deleteById(id);
            return CatMiscResponse.ok().setMetadata("QuizAnswer deleted");
        }catch(Exception e){
            return CatMiscResponse.notFound().addErrorMsgToResponse("Error deleting quizAnswer ",e);
        }
    }

    @PutMapping("/quizanswers/{id}") // done
    public CatMiscResponse updateQuizAnswer(@PathVariable Long id ,@Valid @RequestBody QuizAnswer quizAnswer) {

        QuizAnswer updateQuizAnswer = quizAnswerRepository.findById(id).get();

        updateQuizAnswer.setAnswer(quizAnswer.getAnswer());
        updateQuizAnswer.setValue(quizAnswer.getValue());

        try{
            quizAnswerRepository.save(updateQuizAnswer);
            return CatMiscResponse.ok().setPayload(updateQuizAnswer);
        }catch (Exception e){
            return CatMiscResponse.ok().addErrorMsgToResponse("QuizAnswer not updated: ",e);
        }

    }

}
