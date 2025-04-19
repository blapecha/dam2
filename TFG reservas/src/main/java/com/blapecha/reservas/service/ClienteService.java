package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.Cliente;
import com.blapecha.reservas.repository.ClienteRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findByUsernameAndPassword(String username, String password) {
        return this.clienteRepository.findByUsernameAndPassword(username, password);
    }

    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id).get();
    }

    public void guardarCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }

}