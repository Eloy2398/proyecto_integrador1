package com.apsolutions.dto.query;

import java.math.BigDecimal;

public class ProductoDto {

    private Integer id;
    private String nombre;
    private BigDecimal precio;
    private String value;

    public ProductoDto(Integer id, String nombre, BigDecimal precio) {
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
