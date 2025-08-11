package com.s6.avion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "utilisateurs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    @Column(name = "nom", nullable = false, length = 250)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 250)
    private String prenom;

    @Column(name = "email", nullable = false, unique = true, length = 250)
    private String email;

    @Column(name = "contact", nullable = false, unique = true, length = 50)
    private String contact;

    @Column(name = "mdp", nullable = false, length = 250)
    private String mdp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", nullable = false)
    @ToString.Exclude
    private Role role;
}
