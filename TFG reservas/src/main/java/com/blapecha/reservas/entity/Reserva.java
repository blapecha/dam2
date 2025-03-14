package com.blapecha.reservas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity

public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer precioManyana;
    private Integer precioTarde;
    private String municipio;
    private String local;
    private String dirección;
}
