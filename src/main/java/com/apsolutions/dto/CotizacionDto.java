package com.apsolutions.dto;

import java.util.Date;

public class CotizacionDto {
    private Integer id;

    private Date fecha;

    private Boolean estado;

    private Integer idCliente;

    private String clienteDocumento;

    private String clienteNombre;

    public CotizacionDto(Integer id, Date fecha, Boolean estado, Integer idCliente, String clienteDocumento, String clienteNombre) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.idCliente = idCliente;
        this.clienteDocumento = clienteDocumento;
        this.clienteNombre = clienteNombre;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getClienteDocumento() {
        return clienteDocumento;
    }

    public void setClienteDocumento(String clienteDocumento) {
        this.clienteDocumento = clienteDocumento;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }
}
