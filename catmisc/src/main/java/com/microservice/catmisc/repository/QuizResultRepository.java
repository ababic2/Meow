package com.microservice.catmisc.repository;

import com.microservice.catmisc.entity.QuizResult;
import com.microservice.catmisc.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult,Long> {
}