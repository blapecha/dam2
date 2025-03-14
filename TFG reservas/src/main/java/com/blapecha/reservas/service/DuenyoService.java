package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.Duenyo;
import com.blapecha.reservas.repository.DuenyoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DuenyoService {

    @Autowired
    private DuenyoRepository duenyoRepository;

    public void guardarDuenyo(Duenyo duenyo){
        duenyoRepository.save(duenyo);
    }
}
