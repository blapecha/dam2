package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.service.LocalService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Named
@RequestScoped
@Data
public class LocalesView {

    @Autowired
    private LocalService localService;

    private List<Local> misLocales;


    @PostConstruct
    public void init(){
        misLocales = localService.findAll();

    }
}