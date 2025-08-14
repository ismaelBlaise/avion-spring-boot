package com.s6.avion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories_age")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CategorieAge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie_age")
    private Integer idCategorieAge;

    @Column(name = "categorie", nullable = false, unique = true, length = 50)
    private String categorie;

    @Column(name = "age_min")
    private Integer ageMin;

    @Column(name = "age_max")
    private Integer ageMax;
}
