package com.apsolutions.model;

import jakarta.persistence.*;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean estado;

    @Column(length = 150)
    private String imagen;

    @Column(columnDefinition = "tinyint(1) DEFAULT 0", nullable = false)
    private Byte mostrarweb;

    @Column(columnDefinition = "tinyint(1) DEFAULT 0", nullable = false)
    private Byte mostrardestacado;

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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Byte getMostrarweb() {
        return mostrarweb;
    }

    public void setMostrarweb(Byte mostrarweb) {
        this.mostrarweb = mostrarweb;
    }

    public Byte getMostrardestacado() {
        return mostrardestacado;
    }

    public void setMostrardestacado(Byte mostrardestacado) {
        this.mostrardestacado = mostrardestacado;
    }
}
