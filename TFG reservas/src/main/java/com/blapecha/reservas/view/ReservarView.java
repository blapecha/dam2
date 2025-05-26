
package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Cliente;
import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.Reserva;
import com.blapecha.reservas.model.Sesion;
import com.blapecha.reservas.service.ClienteService;
import com.blapecha.reservas.service.LocalService;
import com.blapecha.reservas.service.ReservaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Slf4j
@Named
@ViewScoped
@Data
public class ReservarView {

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LocalService localService;

    //Campos del formulario
    private LocalDate fecha;
    private boolean tarde;


    public Local local;
    public Cliente cliente;

    public String crearReserva() {
        Sesion sesion = (Sesion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesion");
        this.cliente = clienteService.findById(sesion.getId());
        log.info("Creando Reserva");
        Reserva reserva = new Reserva();
        reserva.setCliente(this.cliente);
        reserva.setLocal(this.local);
        reserva.setTarde(this.tarde);
        reserva.setFecha(this.fecha);
        reservaService.guardar(reserva);
        return "reservas?faces-redirect=true";
    }

    @PostConstruct
    public void init(){
        var idLocalParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("idLocal");
        if(idLocalParam != null){
            Long idLocal = Long.valueOf(idLocalParam);
            this.local = localService.buscarPorId(idLocal);
        }

    }

}