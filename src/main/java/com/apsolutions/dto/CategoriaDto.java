package com.apsolutions.dto;

import jakarta.persistence.Column;
import org.springframework.web.multipart.MultipartFile;

public class CategoriaDto {

    private Integer id;
    private String nombre;

    private Boolean estado;

    private MultipartFile file;

    private Byte mostrarweb;

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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
