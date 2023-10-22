package com.apsolutions.dto;

import java.util.Date;

public class MovimientoListDto {

    private Integer id;
    private Date fecha;
    private Byte tipo;
    private String personaNombre;
    private String descripcion;

    public MovimientoListDto(Integer id, Date fecha, Byte tipo, String personaNombre, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.personaNombre = personaNombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Byte getTipo() {
        return tipo;
    }

    public void setTipo(Byte tipo) {
        this.tipo = tipo;
    }

    public String getPersonaNombre() {
        return personaNombre;
    }

    public void setPersonaNombre(String personaNombre) {
        this.personaNombre = personaNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
