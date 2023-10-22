package com.apsolutions.dto;

import com.apsolutions.model.Cotizacion;
import com.apsolutions.model.Movimientodetalle;
import com.apsolutions.model.Persona;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MovimientoDto {

    private Integer id;
    private Date fecha;
    private Byte tipo;
    private Persona persona;
    private String descripcion;
    private Cotizacion cotizacion;
    private List<Movimientodetalle> movimientodetalleList;

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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public List<Movimientodetalle> getMovimientodetalleList() {
        return movimientodetalleList;
    }

    public void setMovimientodetalleList(List<Movimientodetalle> movimientodetalleList) {
        this.movimientodetalleList = movimientodetalleList;
    }
}
