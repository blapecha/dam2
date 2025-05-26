package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Reserva;
import com.blapecha.reservas.model.Sesion;
import com.blapecha.reservas.service.ReservaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Slf4j
@Named
@SessionScope
@Data
public class ReservasView {

  @Autowired private ReservaService reservaService;

  private List<Reserva> reservas;

  public void eliminarReserva(Long idReserva) {
    Reserva reserva = reservaService.buscarPorId(idReserva);
    reservaService.eliminarReserva(reserva);
  }

  public void buscarReservas() {
    Sesion sesion =
        (Sesion)
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesion");
    if (sesion.isCliente()) {
      this.reservas = reservaService.findByCliente(sesion.getId());
    }else{
        //Es duenyo
        this.reservas = reservaService.findByDuenyo(sesion.getId());
    }
  }

  public String irALocal(Long idLocal) {
    var sesion = (Sesion)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesion");
    if(sesion.isCliente()) {
      return "detalleLocal?id=" + idLocal + "&faces-redirect=true";
    }else{
      return "detalleLocalDuenyo?id=" + idLocal + "&faces-redirect=true";
    }

  }

  public String irACrearResenya(Long idLocal) {
    return "altaResenya?id=" + idLocal + "&faces-redirect=true";
  }

  @PostConstruct
  public void init() {
    //        reservas =reservaService.findAll();
    buscarReservas();
  }
}