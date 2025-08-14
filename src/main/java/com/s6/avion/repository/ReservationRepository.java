package com.s6.avion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s6.avion.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
   
    
}
