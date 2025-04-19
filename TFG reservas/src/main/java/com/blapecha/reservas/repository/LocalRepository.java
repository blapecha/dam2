package com.blapecha.reservas.repository;

import com.blapecha.reservas.entity.Duenyo;
import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.TipoLocalEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    @Query("SELECT DISTINCT l.municipio FROM Local l")
    List<String> findDistinctMunicipios();

    List<Local> findByTipo(TipoLocalEnum tipo);

    List<Local> findByMunicipioIn(List<String> municipios);

    List<Local> findByMunicipioInAndTipo(List<String> municipios, TipoLocalEnum tipo);

    List<Local> findByDuenyo(Duenyo duenyo);

}