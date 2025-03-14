
package com.blapecha.reservas.view;

import com.blapecha.reservas.entity.Local;
import com.blapecha.reservas.entity.Reserva;
import com.blapecha.reservas.service.ReservaService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Named
@RequestScoped
@Data
public class ReservaView {


    @Autowired
    private ReservaService reservaService;

    //Campos del formulario
    public LocalDate fechaInicio;
    public LocalDate fechaFinal;
    public Integer precioManyana;
    public Integer precioTarde;
    public String municipio;
    public String local;
    public String direccion;



    public void enviarFormulario() {
        Reserva reserva = new Reserva();
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFinal);
        reserva.setPrecioManyana(precioManyana);
        reserva.setPrecioTarde(precioTarde);
        reserva.setMunicipio(municipio);
        reserva.setLocal(local);
        reserva.setDirección(direccion);
        reservaService.guardar(reserva);

    }

}