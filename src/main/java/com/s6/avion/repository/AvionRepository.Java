package com.s6.avion.repository;

import com.s6.avion.model.Avion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvionRepository extends JpaRepository<Avion, Integer> {
}
