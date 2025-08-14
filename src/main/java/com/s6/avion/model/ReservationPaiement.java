package com.s6.avion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation_paiements")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ReservationPaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    private Integer idPaiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    @Column(name = "montant", nullable = false)
    private Double montant;


    @Column(name = "date_paiement")
    private LocalDateTime datePaiement = LocalDateTime.now();

    
}
