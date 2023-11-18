package com.apsolutions.dto.query;

public class PersonaDto {
    private Integer id;
    private String documento;
    private String nombre;
    private String value;

    public PersonaDto(Integer id, String documento, String nombre) {
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
