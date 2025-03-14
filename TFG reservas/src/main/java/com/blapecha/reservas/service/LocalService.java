package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.Local;
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


    public void guardar(Local local){
        localRepository.save(local);
    }

    public List<Local> findAll() {
        List<Local> locales = localRepository.findAll();
        return locales;
    }

    public Local buscarPorId(Long id){
        return localRepository.findById(id).get();
    }


    @PostConstruct
    public void init() {
        localRepository.saveAll(buildLocales());
        log.info("Locales inicializados con exito");
    }

    private List<Local> buildLocales(){
        List<Local> locales = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Local local = new Local();
            local.setDireccion("Direccion "+i);
            local.setNombre("Nombre "+i);
            local.setMunicipio("Municipio "+i);
            locales.add(local);
        }
        return locales;
    }
}