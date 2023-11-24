package com.apsolutions.dto.website;

public class MarcaWebsiteDto {
    private Integer id;
    private String nombre;
    private String imagen;

    public MarcaWebsiteDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MarcaWebsiteDto(Integer id, String nombre, String imagen) {
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
