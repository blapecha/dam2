package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Duenyo;
import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.model.Sesion;
import com.blapecha.reservas.service.DuenyoService;
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

    @Autowired
    private DuenyoService duenyoService;

    private List<Local> misLocales;


    @PostConstruct
    public void init(){

        Sesion sesion = (Sesion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesion");
        if(sesion != null){
            if(sesion.isDuenyo()){
                Duenyo duenyo = duenyoService.findById(sesion.getId());
                this.misLocales = localService.findByDuenyo(duenyo);
            }
        }else{
            misLocales = localService.findAll();
        }


    }
}