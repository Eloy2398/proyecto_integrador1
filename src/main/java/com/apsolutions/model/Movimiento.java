package com.apsolutions.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Column(columnDefinition = "tinyint", nullable = false)
    private Byte tipo;
    @ManyToOne
    @JoinColumn(name = "idpersona")
    private Persona persona;
    @Column(length = 150)
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "idcotizacion")
    private Cotizacion cotizacion;
    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean estado;

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

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Byte getTipo() {
        return tipo;
    }

    public void setTipo(Byte tipo) {
        this.tipo = tipo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
