package com.s6.avion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s6.avion.model.Reservation;
import com.s6.avion.model.Utilisateur;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    
    public List<Reservation> findByUtilisateur(Utilisateur utilisateur);
    
}
