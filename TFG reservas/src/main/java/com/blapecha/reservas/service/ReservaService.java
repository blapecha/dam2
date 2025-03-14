package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.Reserva;
import com.blapecha.reservas.repository.ReservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;


    public void guardar(Reserva reserva){
        reservaRepository.save(reserva);
    }


}
