package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.service.LocalService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@Named
@RequestScoped
@Data
public class EjemploView {

    @Autowired
    private LocalService localService;

    //Campos del formulario
    public String nombre;
    public String municipio;
    public String direccion;

    public List<Local> locales;

    public void enviarFormulario() {
        Local local = new Local();
        local.setNombre(nombre);
        local.setMunicipio(municipio);
        local.setDireccion(direccion);
        localService.guardar(local);



    }

    @PostConstruct
    public void init() {

        this.locales = localService.findAll();
        log.info("Locales cargados: {}", this.locales.size());
    }


}