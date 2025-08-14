package com.s6.avion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s6.avion.model.ReservationDetail;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Integer> {
    // Additional query methods can be defined here if needed
    
}
