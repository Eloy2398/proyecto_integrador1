package com.apsolutions.dto.website;

public class CategoriaWebsiteDto {

    private Integer id;
    private String nombre;
    private String nombreUrl;
    private String imagen;

    public CategoriaWebsiteDto(Integer id, String nombre, String nombreUrl, String imagen) {
        this.id = id;
        this.nombreUrl = nombreUrl;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public CategoriaWebsiteDto(Integer id, String nombre, String nombreUrl) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUrl = nombreUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreUrl() {
        return nombreUrl;
    }

    public String getImagen() {
        return imagen;
    }
}
