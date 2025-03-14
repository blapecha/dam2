package com.blapecha.reservas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Resenya {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String cliente;
    private String local;
    private LocalDate fecha;
    private String titulo;
    private String mensaje;
    private Integer puntuacion;
}


