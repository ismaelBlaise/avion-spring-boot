package com.s6.avion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s6.avion.model.Statut;
import com.s6.avion.repository.StatutRepository;

@Service
public class StatutService {
    @Autowired
    private StatutRepository statutRepository; 
    
    public Statut getStatutByName(String statutName) {
        return statutRepository.findByStatut(statutName);
    }
}
