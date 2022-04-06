package com.microservice.catmisc.repository;

import com.microservice.catmisc.entity.QuizAnswer;
import com.microservice.catmisc.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAnswerRepository extends JpaRepository<QuizAnswer,Long> {
}