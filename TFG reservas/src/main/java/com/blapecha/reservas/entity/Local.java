package com.blapecha.reservas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nombre;
    private TipoLocalEnum tipo;
    private Integer precioManyana;
    private Integer precioTarde;
    private String direccion;
    private String municipio;
    private String bloqueado;

    @ManyToOne
    @JoinColumn(name = "duenyo_id", nullable = false)
    private Duenyo duenyo;

}