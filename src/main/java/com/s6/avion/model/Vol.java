package com.s6.avion.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vols")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVol;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private LocalDateTime depart;

    @Column(nullable = false)
    private LocalDateTime arrivee;

    private LocalDateTime finReservation;

    private LocalDateTime finAnnulation;

    @ManyToOne
    @JoinColumn(name = "id_statut")
    private Statut statut;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ville_depart", nullable = false)
    private Ville villeDepart;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ville_arrivee", nullable = false)
    private Ville villeArrivee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_avion", nullable = false)
    private Avion avion;
}
