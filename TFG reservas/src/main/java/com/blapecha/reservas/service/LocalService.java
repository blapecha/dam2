package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.Cliente;
import com.blapecha.reservas.entity.Duenyo;
import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.TipoLocalEnum;
import com.blapecha.reservas.repository.LocalRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LocalService {

  @Autowired
  private LocalRepository localRepository;

  public void guardar(Local local) {
    localRepository.save(local);
  }

  public List<Local> findAll() {
    List<Local> locales = localRepository.findAll();
    return locales;
  }

  public List<Local> findByDuenyo(Duenyo duenyo) {
    List<Local> locales = localRepository.findByDuenyo(duenyo);
    return locales;
  }

  public Local buscarPorId(Long id) {
    return localRepository.findById(id).get();
  }

  public List<String> getMunicipios() {
    return localRepository.findDistinctMunicipios();
  }

  public List<Local> buscarPorMunicipiosYTipo(List<String> municipios, TipoLocalEnum tipo) {
    if(municipios != null && municipios.size() > 0 && tipo != null) {
      return localRepository.findByMunicipioInAndTipo(municipios, tipo);
    }else if(municipios != null && municipios.size() > 0) {
      return localRepository.findByMunicipioIn(municipios);
    }else if(tipo != null) {
      return localRepository.findByTipo(tipo);
    }else{
      return localRepository.findAll();
    }
  }

  @PostConstruct
  public void init() {

  }
}