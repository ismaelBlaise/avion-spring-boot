package com.s6.avion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "avions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvion;

    @Column(nullable = false)
    private Integer capacite;

    @Column(nullable = false, unique = true)
    private String modele;
}
