package com.s6.avion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s6.avion.model.CategorieAge;

public interface CategorieAgeRepository  extends JpaRepository<CategorieAge, Integer> {

    public CategorieAge findByCategorie(String categorie);
    
}
