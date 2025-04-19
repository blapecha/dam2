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
    private String codigo;
    private LocalDate fecha;
    private Boolean tarde;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}