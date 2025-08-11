package com.s6.avion.repository;

import com.s6.avion.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Tu peux ajouter des méthodes spécifiques si besoin, par exemple:
    // Optional<Role> findByRole(String role);
}
