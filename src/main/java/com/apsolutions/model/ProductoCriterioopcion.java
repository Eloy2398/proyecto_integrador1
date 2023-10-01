package com.apsolutions.model;

import jakarta.persistence.*;

@Entity
public class ProductoCriterioopcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idproducto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idcriterioopcion", nullable = false)
    private Criterioopcion criterioopcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Criterioopcion getCriterioopcion() {
        return criterioopcion;
    }

    public void setCriterioopcion(Criterioopcion criterioopcion) {
        this.criterioopcion = criterioopcion;
    }
}
