package com.s6.avion.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.s6.avion.dto.ReservationDetailResponse;
import com.s6.avion.model.Reservation;
import com.s6.avion.model.ReservationDetail;
import com.s6.avion.model.ReservationPaiement;
import com.s6.avion.model.Statut;
import com.s6.avion.model.Utilisateur;
import com.s6.avion.model.Vol;
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

    @Autowired
    private RestTemplate restTemplate;


    
    
    public List<Reservation> getAllReservations(Integer idUtilisateur) {
        Utilisateur utilisateur=utilisateurService.getById(idUtilisateur);
        
        return reservationRepository.findByUtilisateur(utilisateur);
    }


    public void annulerReservation(Integer idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation).orElse(null);

        if (reservation == null) {
            throw new RuntimeException("Réservation introuvable.");
        }

        Vol vol = reservation.getVol();

        // Vérification si la fin d'annulation est configurée
        if (vol.getFinAnnulation() != null) {
            if (LocalDateTime.now().isAfter(vol.getFinAnnulation())) {
                throw new RuntimeException("La période d'annulation est dépassée pour ce vol.");
            }
        }

        // Annulation
        Statut statutAnnule = statutService.getStatutByName("Annulee");
        reservation.setStatut(statutAnnule);
        reservationRepository.save(reservation);
    }



    public void payeeReservation(Integer idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation).orElse(null);
        if (reservation != null) {
            // List<ReservationDetail> reservationDetails = reservationDetailRepository.findByReservation(reservation);
            double montantTotal=0;
            // for (ReservationDetail reservationDetail : reservationDetails) {
            //     montantTotal += reservationDetail.getPrix();
                
            // }
            // if(montantTotal <= 0) {
            //     throw new IllegalArgumentException("Aucune réservation à payer ou montant invalide.");
            // }
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



    private final String baseUrl = "http://localhost:8080/avion-framework";

   
    public ReservationDetailResponse getReservationDetails(int idReservation) {
        String url = baseUrl + "/reservation-details-json?id=" + idReservation;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<ReservationDetailResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    ReservationDetailResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody();
            } else {
                throw new RuntimeException("Erreur lors de la récupération des détails: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'appel API: " + e.getMessage(), e);
        }
    }
    
}
