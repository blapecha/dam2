package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.Cliente;
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

    public Duenyo findByUsernameAndPassword(String username, String password) {
        return this.duenyoRepository.findByUsernameAndPassword(username, password);
    }

    public Duenyo findById(Long id) {
        return this.duenyoRepository.findById(id).get();
    }

    public void guardarDuenyo(Duenyo duenyo){
        duenyoRepository.save(duenyo);
    }
}