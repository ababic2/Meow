package com.microservice.catmisc.repository;

        import com.microservice.catmisc.entity.QuizQuestion;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion,Long> {
}