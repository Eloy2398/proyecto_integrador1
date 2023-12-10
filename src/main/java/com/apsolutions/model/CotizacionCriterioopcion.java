package com.apsolutions.model;

import jakarta.persistence.*;

@Entity
public class CotizacionCriterioopcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "idcotizacion", nullable = false)
    private Cotizacion cotizacion;
    @ManyToOne
    @JoinColumn(name = "idcriterioopcion", nullable = false)
    private Criterioopcion criterioopcion;

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

    public Criterioopcion getCriterioopcion() {
        return criterioopcion;
    }

    public void setCriterioopcion(Criterioopcion criterioopcion) {
        this.criterioopcion = criterioopcion;
    }
}
