package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.Resenya;
import com.blapecha.reservas.service.LocalService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Named
@RequestScoped
@Data
public class DetalleLocalView {

    @Autowired
    private LocalService localService;

    private Local local;

    private List<Resenya> resenyas;


    @PostConstruct
    public void init() {
        Long idLocal = Long.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id"));

        local = localService.buscarPorId(idLocal);

        resenyas = new ArrayList<>();
        Resenya resenya = new Resenya();
        resenya.setFecha(LocalDate.now());
        resenya.setTitulo("El sitio más guarro que he visto");
        resenya.setMensaje("Todo sucio, goteras, mierda por todas partes, no volveremos que asco");
        resenya.setPuntuacion(1);
        resenyas.add(resenya);
        resenya = new Resenya();
        resenya.setFecha(LocalDate.now());
        resenya.setTitulo("El sitio esta genial");
        resenya.setMensaje("Nos lo hemos pasado muy bien");
        resenya.setPuntuacion(3);
        resenyas.add(resenya);

    }
}