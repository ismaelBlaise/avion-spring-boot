package com.s6.avion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "villes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVille;

    @Column(nullable = false, unique = true)
    private String nomVille;
}
