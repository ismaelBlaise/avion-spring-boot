package com.s6.avion.repository;

import com.s6.avion.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    // Méthodes utiles pour rechercher un utilisateur par email ou contact
    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findByContact(String contact);
}
