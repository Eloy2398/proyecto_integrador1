package com.apsolutions.dto;

import com.apsolutions.model.Cliente;
import com.apsolutions.model.Cotizaciondetalle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CotizacionDto {
    private Integer id;

    private Date fecha;

    private Cliente cliente;

    private List<Cotizaciondetalle> cotizaciondetalleList;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cotizaciondetalle> getCotizaciondetalleList() {
        return cotizaciondetalleList;
    }

    public void setCotizaciondetalleList(List<Cotizaciondetalle> cotizaciondetalleList) {
        this.cotizaciondetalleList = cotizaciondetalleList;
    }
}
