package com.blapecha.reservas.repository;

import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.Resenya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenyaRepository extends JpaRepository<Resenya, Long> {

    public List<Resenya> findByLocal(Local local);

}