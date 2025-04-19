package com.blapecha.reservas.view;


import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.TipoLocalEnum;
import com.blapecha.reservas.model.Sesion;
import com.blapecha.reservas.service.LocalService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Named
@RequestScoped
@Data
public class IndexView {

    @Autowired
    private LocalService localService;

    //Campo de formulario
    private List<String> municipiosSeleccionados;
    private TipoLocalEnum tipoSeleccionado;

    //Valores para el formulario
    private List<String> municipios;
    private List<TipoLocalEnum> tipos;

    //Resultado de la busqueda
    private List<Local> locales;

    private boolean login;

    public void buscar(){
        log.info("Municipios seleccionados: {} - tipo selccionado {}", municipiosSeleccionados,tipoSeleccionado);
        this.locales = localService.buscarPorMunicipiosYTipo(municipiosSeleccionados, tipoSeleccionado);
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sesion");
        this.login = false;
        return "index?faces-redirect=true";
    }

    @PostConstruct
    public void init() {
        this.municipios = this.localService.getMunicipios();
        tipos = Arrays.stream(TipoLocalEnum.values()).toList();
        this.locales = localService.findAll();
        if((Sesion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesion") !=null){
            login = true;
        }
    }

}