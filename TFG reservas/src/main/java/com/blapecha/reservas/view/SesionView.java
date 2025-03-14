package com.blapecha.reservas.view;

import com.blapecha.reservas.service.SesionService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
@Named
@RequestScoped
@Data
public class SesionView {

    @Autowired
    private SesionService sesionService;


    //Campos del formulario
    public String user;
    public String password;


    public String enviarFormulario() {
    log.info("usuario:{} password:{}" ,user,password);
    return "locales";
    }
}