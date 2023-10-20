package com.apsolutions.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Movimientodetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "idmovimiento", nullable = false)
    private Movimiento movimiento;
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

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
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
