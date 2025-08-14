package com.s6.avion.repository;

import com.s6.avion.model.ConfVol;
import com.s6.avion.pk.ConfVolId;
import com.s6.avion.model.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConfVolRepository extends JpaRepository<ConfVol, ConfVolId> {
    List<ConfVol> findByVol(Vol vol);
}
