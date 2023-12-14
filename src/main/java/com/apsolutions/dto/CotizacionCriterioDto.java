package com.apsolutions.dto;

import com.apsolutions.model.CotizacionCriterioopcion;

import java.util.Date;
import java.util.List;

public class CotizacionCriterioDto {

    private Integer id;

    private Date fecha;

    private Integer idCliente;

    private String nombreCliente;

    private Byte origen;

    private List<CotizaciondetalleDto> cotizaciondetalleList;

    private List<CotizacionCriterioOpcionDto> cotizacionCriterioOpcionDtoList;

    public CotizacionCriterioDto(Date fecha, Integer idCliente, String nombreCliente) {
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

    public List<CotizaciondetalleDto> getCotizaciondetalleList() {
        return cotizaciondetalleList;
    }

    public void setCotizaciondetalleList(List<CotizaciondetalleDto> cotizaciondetalleList) {
        this.cotizaciondetalleList = cotizaciondetalleList;
    }

    public List<CotizacionCriterioOpcionDto> getCotizacionCriterioOpcionDtoList() {
        return cotizacionCriterioOpcionDtoList;
    }

    public void setCotizacionCriterioOpcionDtoList(List<CotizacionCriterioOpcionDto> cotizacionCriterioOpcionDtoList) {
        this.cotizacionCriterioOpcionDtoList = cotizacionCriterioOpcionDtoList;
    }
}
