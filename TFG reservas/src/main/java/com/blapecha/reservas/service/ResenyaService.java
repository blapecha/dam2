package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.Resenya;
import com.blapecha.reservas.repository.ResenyaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenyaService {

    @Autowired
    private ResenyaRepository resenyaRepository;

    @Autowired
    private LocalService localService;

    public void crearResenya(Resenya resenya) {
        resenyaRepository.save(resenya);
    }

    public List<Resenya> getResenyas(Local local) {
        return resenyaRepository.findByLocal(local);
    }
}