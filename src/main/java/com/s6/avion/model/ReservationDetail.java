package com.s6.avion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation_details")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ReservationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation_detail")
    private Integer idReservationDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation", nullable = false)
    @ToString.Exclude
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie_age", nullable = false)
    @ToString.Exclude
    private CategorieAge categorieAge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe", nullable = false)
    @ToString.Exclude
    private Classe classe;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @Lob
    @Column(name = "passeport")
    private byte[] passeport;

    @Column(name = "nom_fichier", unique = true, length = 255)
    private String nomFichier;

    @Column(name = "date_depot")
    private LocalDateTime dateDepot;
}
