package com.microservice.donation.repository;

import com.microservice.donation.entity.Donation;
import com.microservice.donation.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findTop3ByOrderByAmountDesc();
    Donation findTopByOrderByAmountDesc();
    List<Donation> findByUserId(Long id);

    @Query("SELECT d FROM Donation d Order BY d.amount")
    List<Donation> findTopUserDefined(Pageable pageable);
}
