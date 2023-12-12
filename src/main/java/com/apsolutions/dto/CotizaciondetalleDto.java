package com.apsolutions.dto;

import java.math.BigDecimal;

public class CotizaciondetalleDto {
    private Integer idProducto;
    private String nombreProducto;
    private Short cantidad;
    private BigDecimal precio;

    public CotizaciondetalleDto() {
    }

    public CotizaciondetalleDto(Integer idProducto, String nombreProducto, Short cantidad, BigDecimal precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
