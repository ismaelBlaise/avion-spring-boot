package com.s6.avion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s6.avion.model.Reservation;
import com.s6.avion.model.Utilisateur;
import com.s6.avion.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UtilisateurService utilisateurService;
    
    
    public List<Reservation> getAllReservations(Integer idUtilisateur) {
        Utilisateur utilisateur=utilisateurService.getById(idUtilisateur);
        
        return reservationRepository.findByUtilisateur(utilisateur);
    }
    
}
