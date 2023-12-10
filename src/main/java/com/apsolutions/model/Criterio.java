package com.apsolutions.model;

import jakarta.persistence.*;

@Entity
public class Criterio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(columnDefinition = "tinyint", nullable = false)
    private Byte ordenMostrar;

    @Column(columnDefinition = "tinyint(1)", nullable = false)
    private Byte nivelImportancia;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Byte getOrdenMostrar() {
        return ordenMostrar;
    }

    public void setOrdenMostrar(Byte ordenMostrar) {
        this.ordenMostrar = ordenMostrar;
    }

    public Byte getNivelImportancia() {
        return nivelImportancia;
    }

    public void setNivelImportancia(Byte nivelImportancia) {
        this.nivelImportancia = nivelImportancia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
