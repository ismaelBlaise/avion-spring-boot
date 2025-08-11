package com.s6.avion.service;

import com.s6.avion.model.Utilisateur;
import com.s6.avion.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    
    public Utilisateur login(String email, String mdp) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email: " + email));

        if (!utilisateur.getMdp().equals(mdp)) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return utilisateur;
    }

     
}
