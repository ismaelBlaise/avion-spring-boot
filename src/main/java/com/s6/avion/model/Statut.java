package com.s6.avion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "statuts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatut;

    @Column(nullable = false, unique = true)
    private String statut;

    @Column(nullable = false)
    private String source;
}
