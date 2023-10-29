package com.apsolutions.dto;

public class PersonaBusquedaDto {
    private Integer id;
    private String documento;
    private String nombre;
    private String value;

    public PersonaBusquedaDto(Integer id, String documento, String nombre) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValue() {
        return nombre;
    }
}