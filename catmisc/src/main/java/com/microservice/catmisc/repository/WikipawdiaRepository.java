package com.microservice.catmisc.repository;

import com.microservice.catmisc.entity.Vet;
import com.microservice.catmisc.entity.Wikipawdia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikipawdiaRepository extends JpaRepository<Wikipawdia,Long> {
}