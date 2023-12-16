package com.apsolutions.dto;

public class ProductoCaracteristicaDto {

    private Integer id;
    private String nombre;
    private String valor;

    public ProductoCaracteristicaDto(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public ProductoCaracteristicaDto(Integer id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
