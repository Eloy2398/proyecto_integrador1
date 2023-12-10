package com.apsolutions.dto;

import com.apsolutions.model.CotizacionCriterioopcion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CotizacionDto {
    private Integer id;

    private Date fecha;

    private Integer idCliente;

    private String nombreCliente;

    private Byte origen;
    private List<CotizacionCriterioopcion> cotizacionCriterioopcionList;

    private List<CotizaciondetalleDto> cotizaciondetalleList;

    public CotizacionDto() {
    }

    public CotizacionDto(Date fecha, Integer idCliente, String nombreCliente) {
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
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

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Byte getOrigen() {
        return origen;
    }

    public void setOrigen(Byte origen) {
        this.origen = origen;
    }

    public List<CotizacionCriterioopcion> getCotizacionCriterioopcionList() {
        return cotizacionCriterioopcionList;
    }

    public void setCotizacionCriterioopcionList(List<CotizacionCriterioopcion> cotizacionCriterioopcionList) {
        this.cotizacionCriterioopcionList = cotizacionCriterioopcionList;
    }

    public List<CotizaciondetalleDto> getCotizaciondetalleList() {
        return cotizaciondetalleList;
    }

    public void setCotizaciondetalleList(List<CotizaciondetalleDto> cotizaciondetalleList) {
        this.cotizaciondetalleList = cotizaciondetalleList;
    }
}
