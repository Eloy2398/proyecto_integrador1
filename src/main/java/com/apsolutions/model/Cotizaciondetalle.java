package com.apsolutions.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Cotizaciondetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idcotizacion", nullable = false)
    private Cotizacion cotizacion;

    @ManyToOne
    @JoinColumn(name = "idproducto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Short cantidad;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal precio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
