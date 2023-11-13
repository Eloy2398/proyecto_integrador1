package com.apsolutions.dto;

public class ClienteBusquedaDto {
    private Integer id;
    private String documento;
    private String nombre;

    public ClienteBusquedaDto(Integer id, String documento, String nombre) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
