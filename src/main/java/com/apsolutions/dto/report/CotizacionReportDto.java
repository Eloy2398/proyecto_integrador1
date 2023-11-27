package com.apsolutions.dto.report;

import java.util.Date;

public class CotizacionReportDto {
    private Integer idCotizacion;

    private Date fecha;

    private String docCliente;

    private String nomCliente;

    private Byte estado;

    private Byte origen;

    public CotizacionReportDto() {
    }

    public CotizacionReportDto(Integer idCotizacion, Date fecha, String docCliente, String nomCliente, Byte estado, Byte origen) {
        this.idCotizacion = idCotizacion;
        this.fecha = fecha;
        this.docCliente = docCliente;
        this.nomCliente = nomCliente;
        this.estado = estado;
        this.origen = origen;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDocCliente() {
        return docCliente;
    }

    public void setDocCliente(String docCliente) {
        this.docCliente = docCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public Byte getEstado() {
        return estado;
    }

    public void setEstado(Byte estado) {
        this.estado = estado;
    }

    public Byte getOrigen() {
        return origen;
    }

    public void setOrigen(Byte origen) {
        this.origen = origen;
    }
}
