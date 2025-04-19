package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.Cliente;
import com.blapecha.reservas.entity.Reserva;
import com.blapecha.reservas.repository.ClienteRepository;
import com.blapecha.reservas.repository.ReservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service

public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public void guardar(Reserva reserva){
        reserva = reservaRepository.save(reserva);
        reserva.setCodigo("RES-"+reserva.getId());
        reservaRepository.save(reserva);
    }

    public Reserva buscarPorId(Long idReserva){
        return reservaRepository.findById(idReserva).get();
    }

    public void eliminarReserva(Reserva reserva){
        reservaRepository.delete(reserva);
    }

    public List<Reserva> findByCliente(Long idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).get();
        return reservaRepository.findByCliente(cliente);
    }

    public List<Reserva> findAll(){
       return reservaRepository.findAll();

    }
}