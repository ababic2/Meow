package com.microservice.catmisc.entity;

import javax.persistence.*;

@Entity
@Table(name="quizresult")
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rangelow", nullable=false)
    private Integer rangeLow;

    @Column(name="rangehigh", nullable=false)
    private Integer rangeHigh;

    @Column(name="result", nullable=false)
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public QuizResult(Long id, Integer rangeLow, Integer rangeHigh, String result, Quiz quiz) {
        this.id = id;
        this.rangeLow = rangeLow;
        this.rangeHigh = rangeHigh;
        this.result = result;
        this.quiz = quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRangeLow() {
        return rangeLow;
    }

    public void setRangeLow(Integer rangeLow) {
        this.rangeLow = rangeLow;
    }

    public Integer getRangeHigh() {
        return rangeHigh;
    }

    public void setRangeHigh(Integer rangeHigh) {
        this.rangeHigh = rangeHigh;
    }

    public QuizResult() {
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

}
