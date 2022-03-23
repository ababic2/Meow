package com.microservice.cat.repository;
import com.microservice.cat.entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepository extends JpaRepository<Health,Long> {



}
