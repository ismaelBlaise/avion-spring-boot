package com.s6.avion.model;

import jakarta.persistence.*;
import lombok.*;


import com.s6.avion.pk.ConfVolId;

@Entity
@Table(name = "conf_vol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(ConfVolId.class)  
public class ConfVol {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_vol", nullable = false)
    private Vol vol;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_classe", nullable = false)
    private Classe classe;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_categorie_age", nullable = false)
    private CategorieAge categorieAge;

    @Column(nullable = false)
    private Double montant;

    @Column(nullable = false)
    private Integer capacite;

    
    @Transient
    public String getNomClasse() {
        return classe != null ? classe.getClasse() : null;
    }

    @Transient
    public String getCategorieAgeNom() {
        return categorieAge != null ? categorieAge.getCategorie() : null;
    }
}
