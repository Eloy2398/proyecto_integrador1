package com.apsolutions.dto.website;

public class MarcaDto {
    private Integer id;
    private String nombre;
    private String imagen;

    public MarcaDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MarcaDto(Integer id, String nombre, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }
}
