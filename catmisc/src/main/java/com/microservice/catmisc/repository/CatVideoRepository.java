package com.microservice.catmisc.repository;

import com.microservice.catmisc.entity.CatVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatVideoRepository extends JpaRepository<CatVideo,Long> {
}
