package com.microservice.catmisc.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="quizquestion")
public class QuizQuestion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="question", nullable=false)
    private String answer;

    @OneToMany( mappedBy = "quizQuestion")
    private List<QuizAnswer> quizAnswers= new ArrayList<>();

    public QuizQuestion(Long id, String answer, List<QuizAnswer> quizAnswers, Quiz quiz) {
        this.id = id;
        this.answer = answer;
        this.quizAnswers = quizAnswers;
        this.quiz = quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<QuizAnswer> getQuizAnswers() {
        return quizAnswers;
    }

    public void setQuizAnswers(List<QuizAnswer> quizAnswers) {
        this.quizAnswers = quizAnswers;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public QuizQuestion() {
    }

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;



}
