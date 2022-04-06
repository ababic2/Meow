package com.microservice.catmisc.repository;

import com.microservice.catmisc.entity.CatVideo;
import com.microservice.catmisc.entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet,Long> {
}