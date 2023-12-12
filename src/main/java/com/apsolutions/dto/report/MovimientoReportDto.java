package com.apsolutions.dto.report;

import java.math.BigDecimal;
import java.util.Date;

public class MovimientoReportDto {
    private Integer idMovimiento;

    private Date fecha;

    private String tipooperacion;

    private Short cantidad;

    private BigDecimal precio;

    public MovimientoReportDto() {
    }

    public MovimientoReportDto(Integer idMovimiento, Date fecha, String tipooperacion, Short cantidad, BigDecimal precio) {
        this.idMovimiento = idMovimiento;
        this.fecha = fecha;
        this.tipooperacion = tipooperacion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipooperacion() {
        return tipooperacion;
    }

    public void setTipooperacion(String tipooperacion) {
        this.tipooperacion = tipooperacion;
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
