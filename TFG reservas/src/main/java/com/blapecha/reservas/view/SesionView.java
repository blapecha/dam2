package com.blapecha.reservas.view;

import com.blapecha.reservas.model.Sesion;
import com.blapecha.reservas.service.SesionService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
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
public class SesionView {

  @Autowired private SesionService sesionService;

  // Campos del formulario
  public String user;
  public String password;

  public String enviarFormulario() {
    log.info("usuario:{} password:{}", user, password);
    Sesion sesion = sesionService.iniciaSesion(user, password);

    if (sesion != null) {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesion", sesion);
      if(sesion.isCliente()){
        return "cliente_index?faces-redirect=true\n";
      }else{
        return "locales?faces-redirect=true\n";
      }
    }
    return "cliente_index?faces-redirect=true\n";
  }
}