package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Cliente;
import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.Resenya;
import com.blapecha.reservas.model.Sesion;
import com.blapecha.reservas.service.ClienteService;
import com.blapecha.reservas.service.LocalService;
import com.blapecha.reservas.service.ResenyaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Named
@SessionScope
@Data
public class AltaResenyaView {

    @Autowired
    private LocalService localService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ResenyaService resenyaService;

    private Sesion sesion;

    private Local local;

    private String titulo;
    private String mensaje;
    private Integer puntuacion;

    public String crearResenya(){

        Cliente cliente = clienteService.findById(this.sesion.getId());

        Resenya resenya = new Resenya();
        resenya.setLocal(local);
        resenya.setTitulo(titulo);
        resenya.setMensaje(mensaje);
        resenya.setPuntuacion(puntuacion);
        resenya.setCliente(cliente);
        resenyaService.crearResenya(resenya);

        return "detalleLocal?id="+local.getId()+"&faces-redirect=true";
    }

    @PostConstruct
    public void init() {
        var idLocalParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if(idLocalParam != null){
            Long idLocal = Long.valueOf(idLocalParam);
            this.local = localService.buscarPorId(idLocal);
        }

        this.sesion = (Sesion)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesion");

    }
}