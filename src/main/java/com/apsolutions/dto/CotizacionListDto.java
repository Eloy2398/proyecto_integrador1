package com.apsolutions.dto;

import java.util.Date;

public class CotizacionListDto {
    private Integer id;

    private Date fecha;

    private Byte estado;

    private Integer idCliente;

    private String clienteDocumento;

    private String clienteNombre;

    public CotizacionListDto(Integer id, Date fecha, Byte estado, Integer idCliente, String clienteDocumento, String clienteNombre) {
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

    public Byte getEstado() {
        return estado;
    }

    public void setEstado(Byte estado) {
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
