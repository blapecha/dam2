package com.blapecha.reservas.repository;

import com.blapecha.reservas.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}