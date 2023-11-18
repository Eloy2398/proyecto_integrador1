package com.apsolutions.dto.query;

public class CotizacionDto {

    private Integer id;
    private Integer idPersona;
    private String nombreCliente;
    private String value;

    public CotizacionDto(Integer id, Integer idPersona, String nombreCliente) {
        this.id = id;
        this.idPersona = idPersona;
        this.nombreCliente = nombreCliente;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getValue() {
        return String.valueOf(id);
    }
}
