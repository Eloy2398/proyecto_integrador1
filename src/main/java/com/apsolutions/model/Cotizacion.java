package com.apsolutions.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;

    @Column(columnDefinition = "tinyint(1) DEFAULT 1", nullable = false)
    private Byte estado;

    @Column(columnDefinition = "tinyint(1) DEFAULT 1", nullable = false)
    private Byte origen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Byte getEstado() {
        return estado;
    }

    public void setEstado(Byte estado) {
        this.estado = estado;
    }

    public Byte getOrigen() {
        return origen;
    }

    public void setOrigen(Byte origen) {
        this.origen = origen;
    }
}
