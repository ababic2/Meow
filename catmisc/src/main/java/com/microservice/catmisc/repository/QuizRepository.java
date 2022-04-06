package com.microservice.catmisc.repository;

import com.microservice.catmisc.entity.Quiz;
import com.microservice.catmisc.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
}

