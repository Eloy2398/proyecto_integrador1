package com.apsolutions.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MovimientoDto {

    private Integer id;
    private Date fecha;
    private Byte tipo;
    private Integer idPersona;
    private String nombrePersona;
    private String descripcion;
    private Integer idCotizacion;
    private Boolean estado;
    private List<MovimientodetalleDto> movimientodetalleList;

    public MovimientoDto() {
    }

    public MovimientoDto(Date fecha, Byte tipo, String nombrePersona, String descripcion, Boolean estado, Integer idCotizacion) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.nombrePersona = nombrePersona;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idCotizacion = idCotizacion;
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

    public void setFecha(String fecha) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.fecha = format.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Byte getTipo() {
        return tipo;
    }

    public void setTipo(Byte tipo) {
        this.tipo = tipo;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<MovimientodetalleDto> getMovimientodetalleList() {
        return movimientodetalleList;
    }

    public void setMovimientodetalleList(List<MovimientodetalleDto> movimientodetalleList) {
        this.movimientodetalleList = movimientodetalleList;
    }
}
