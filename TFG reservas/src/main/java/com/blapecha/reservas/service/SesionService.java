package com.blapecha.reservas.service;

import com.blapecha.reservas.entity.Cliente;
import com.blapecha.reservas.entity.Duenyo;
import com.blapecha.reservas.model.Sesion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SesionService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DuenyoService duenyoService;

    public Sesion iniciaSesion(String username, String password) {
        Cliente cliente = clienteService.findByUsernameAndPassword(username, password);
        if(cliente != null) {
            Sesion sesion = new Sesion();
            sesion.setId(cliente.getId());
            sesion.setNombre(cliente.getNombre());
            sesion.setApellidos(cliente.getApellidos());
            sesion.setDuenyo(false);
            sesion.setCliente(true);
            return sesion;
        }else{
            Duenyo duenyo = duenyoService.findByUsernameAndPassword(username, password);
            if(duenyo != null) {
                Sesion sesion = new Sesion();
                sesion.setId(duenyo.getId());
                sesion.setNombre(duenyo.getNombre());
                sesion.setApellidos(duenyo.getApellidos());
                sesion.setDuenyo(true);
                sesion.setCliente(false);
                return sesion;
            }

        }
        return null;
    }

}