package com.s6.avion.service;

import com.s6.avion.model.Vol;
import com.s6.avion.repository.VolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolService {

    private final VolRepository volRepository;

    public List<Vol> getAllVols() {
        return volRepository.findAll();
    }
}
