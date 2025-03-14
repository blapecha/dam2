package com.blapecha.reservas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Duenyo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String username;
    private String password;

}