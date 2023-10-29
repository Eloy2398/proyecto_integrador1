package com.apsolutions.dto;

import java.math.BigDecimal;

public class ProductoBusquedaDto {

    private Integer id;
    private String nombre;
    private BigDecimal precio;
    private String value;

    public ProductoBusquedaDto(Integer id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getValue() {
        return nombre;
    }
}
