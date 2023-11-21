package com.apsolutions.dto.report;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovimientoReportDto {
    private Integer idMovimiento;

    private Date fecha;

    private Date fecha1;

    private Date fecha2;

    private Integer idProducto;

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

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.fecha1 = format.parse(fecha1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.fecha2 = format.parse(fecha2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
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
