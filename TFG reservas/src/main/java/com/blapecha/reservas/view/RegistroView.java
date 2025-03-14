package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Cliente;
import com.blapecha.reservas.entity.Duenyo;
import com.blapecha.reservas.service.ClienteService;
import com.blapecha.reservas.service.DuenyoService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Named
@RequestScoped
@Data
public class RegistroView {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DuenyoService duenyoService;

    //Campos del registro
    public String nombre;
    public String apellidos;
    public String telefono;
    public String email;
    public String user;
    public String password;
    public boolean duenyo;

    public void darDeAlta() {

        if(duenyo){
            Duenyo d = new Duenyo();
            d.setNombre(nombre);
            d.setApellidos(apellidos);
            d.setTelefono(telefono);
            d.setEmail(email);
            d.setUsername(user);
            d.setPassword(password);
            duenyoService.guardarDuenyo(d);
        }else{
            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setApellidos(apellidos);
            cliente.setTelefono(telefono);
            cliente.setEmail(email);
            cliente.setUsername(user);
            cliente.setPassword(password);
            clienteService.guardarCliente(cliente);
        }
        limpiarFormulario();
    }

    public void limpiarFormulario(){
        nombre = "";
        apellidos = "";
        telefono = "";
        email = "";
        user = "";
        password = "";
        duenyo = false;
    }

    @PostConstruct
    public void init() {

    }


}