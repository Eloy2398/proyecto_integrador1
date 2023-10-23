package com.apsolutions.model;

import jakarta.persistence.*;

@Entity
public class ProductoCaracteristica {
    @EmbeddedId
    private ProductoCaracteristicaPK id;

    @ManyToOne
    @JoinColumn(name = "idproducto", insertable = false, updatable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idcaracteristica", insertable = false, updatable = false)
    private Caracteristica caracteristica;

    @Column(length = 50)
    private String valor;

    public ProductoCaracteristicaPK getId() {
        return id;
    }

    public void setId(ProductoCaracteristicaPK id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
