package com.blapecha.reservas.entity;

public enum TipoLocalEnum {
    FIESTAS("Fiestas"),
    PARQUE_INFANTIL("Parque Infantil"),
    SALON_DE_EVENTOS("Salón de Eventos");

    private final String name;

    TipoLocalEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}