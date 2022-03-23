package com.microservice.cat.repository;
import com.microservice.cat.entity.Illnesses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IllnessRepository extends JpaRepository<Illnesses,Long> {



}
