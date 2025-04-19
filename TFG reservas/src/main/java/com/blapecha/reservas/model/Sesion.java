package com.blapecha.reservas.model;

import lombok.Data;

@Data
public class Sesion {

    private Long id;
    private String nombre;
    private String apellidos;
    private boolean cliente;
    private boolean duenyo;
}