package com.blapecha.reservas.repository;

import com.blapecha.reservas.entity.Duenyo;
import com.blapecha.reservas.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DuenyoRepository  extends JpaRepository<Duenyo, Long> {
}
