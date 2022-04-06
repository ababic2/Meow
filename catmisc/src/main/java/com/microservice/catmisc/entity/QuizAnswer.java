package com.microservice.catmisc.entity;

import javax.persistence.*;

@Entity
@Table(name="quizanswer")
public class QuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="answer", nullable=false)
    private String answer;

    @Column(name="value", nullable=false)
    private Integer value;

    public QuizAnswer(Long id, String answer, Integer value, QuizQuestion quizQuestion) {
        this.id = id;
        this.answer = answer;
        this.value = value;
        this.quizQuestion = quizQuestion;
    }

    public QuizAnswer() {
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "quizquestion_id", referencedColumnName = "id")
    private QuizQuestion quizQuestion;

    public QuizQuestion getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(QuizQuestion quizQuestion) {
        this.quizQuestion = quizQuestion;
    }
}
