package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Duenyo;
import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.TipoLocalEnum;
import com.blapecha.reservas.model.Sesion;
import com.blapecha.reservas.service.DuenyoService;
import com.blapecha.reservas.service.LocalService;
import com.blapecha.reservas.service.SesionService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Named
@RequestScoped
@Data
public class AltaLocalView {

    @Autowired
    private LocalService sesionService;

    @Autowired
    private DuenyoService duenyoService;

    private String nombre;
    private TipoLocalEnum tipo;
    private Integer precioManyana;
    private Integer precioTarde;
    private String direccion;
    private String municipio;
    @Autowired
    private LocalService localService;

    public TipoLocalEnum[] getTipos() {
        return TipoLocalEnum.values();
    }

    public String crearLocal() {
        Sesion sesion = (Sesion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesion");
        Duenyo duenyo = duenyoService.findById(sesion.getId());
        Local local = new Local();
        local.setDireccion(direccion);
        local.setMunicipio(municipio);
        local.setTipo(tipo);
        local.setNombre(nombre);
        local.setDuenyo(duenyo);
        localService.guardar(local);

        return "locales?faces-redirect=true";
    }
}