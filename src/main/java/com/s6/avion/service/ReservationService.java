package com.s6.avion.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s6.avion.model.Reservation;
import com.s6.avion.model.ReservationDetail;
import com.s6.avion.model.ReservationPaiement;
import com.s6.avion.model.Statut;
import com.s6.avion.model.Utilisateur;
import com.s6.avion.repository.ReservationDetailRepository;
import com.s6.avion.repository.ReservationPaiementRepository;
import com.s6.avion.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private StatutService statutService;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private ReservationPaiementRepository reservationPaiementRepository;

    
    
    
    public List<Reservation> getAllReservations(Integer idUtilisateur) {
        Utilisateur utilisateur=utilisateurService.getById(idUtilisateur);
        
        return reservationRepository.findByUtilisateur(utilisateur);
    }


    public void annulerReservation(Integer idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation).orElse(null);
        if (reservation != null) {
            Statut statutAnnule = statutService.getStatutByName("Annulee");
            reservation.setStatut(statutAnnule);
            reservationRepository.save(reservation);
        }
    }


    public void payeeReservation(Integer idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation).orElse(null);
        if (reservation != null) {
            List<ReservationDetail> reservationDetails = reservationDetailRepository.findByReservation(reservation);
            double montantTotal=0;
            for (ReservationDetail reservationDetail : reservationDetails) {
                montantTotal += reservationDetail.getPrix();
                
            }
            if(montantTotal <= 0) {
                throw new IllegalArgumentException("Aucune réservation à payer ou montant invalide.");
            }
            ReservationPaiement reservationPaiement = new ReservationPaiement();
            reservationPaiement.setReservation(reservation);
            reservationPaiement.setMontant(montantTotal);
            reservationPaiement.setDatePaiement(LocalDateTime.now());
            reservationPaiementRepository.save(reservationPaiement);
            Statut statutPayee = statutService.getStatutByName("Payee");
            reservation.setStatut(statutPayee);
            reservationRepository.save(reservation);
        }
    }
    
}
