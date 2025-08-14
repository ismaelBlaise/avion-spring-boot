package com.s6.avion.repository;

import com.s6.avion.model.ReservationPaiement;
import com.s6.avion.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationPaiementRepository extends JpaRepository<ReservationPaiement, Integer> {

    List<ReservationPaiement> findByReservation(Reservation reservation);

}
