package com.blapecha.reservas.repository;

import com.blapecha.reservas.entity.Cliente;
import com.blapecha.reservas.entity.Duenyo;
import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    public List<Reserva> findByCliente(Cliente cliente);

    public List<Reserva> findByLocal_Duenyo(Duenyo duenyo);

    public List<Reserva> findByLocal(Local local);

}