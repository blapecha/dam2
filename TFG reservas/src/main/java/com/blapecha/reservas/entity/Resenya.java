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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;
    private LocalDate fecha;
    private String titulo;
    private String mensaje;
    private Integer puntuacion;
}