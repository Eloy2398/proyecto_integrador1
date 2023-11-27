package com.apsolutions.dto.report;

import java.math.BigDecimal;
import java.util.Date;

public class MovimientoReportDto {
    private Integer idMovimiento;

    private Date fecha;

    private Byte tipooperacion;

    private Short cantidad;

    private BigDecimal precio;

    public MovimientoReportDto() {
    }

    public MovimientoReportDto(Integer idMovimiento, Date fecha, Byte tipooperacion, Short cantidad, BigDecimal precio) {
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

    public Byte getTipooperacion() {
        return tipooperacion;
    }

    public void setTipooperacion(Byte tipooperacion) {
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
