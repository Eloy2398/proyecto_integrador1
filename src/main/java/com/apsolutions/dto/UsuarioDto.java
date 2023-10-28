package com.apsolutions.dto;

public class UsuarioDto {

    private Integer id;

    private String nombre;

    private String usuario;

    private Byte bloqueado;

    private String perfilNombre;

    private Integer perfilId;

    public UsuarioDto(Integer id, String nombre, String usuario, Byte bloqueado, String perfilNombre, Integer perfilId) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.bloqueado = bloqueado;
        this.perfilNombre = perfilNombre;
        this.perfilId = perfilId;
    }

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Byte getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Byte bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getPerfilNombre() {
        return perfilNombre;
    }

    public void setPerfilNombre(String perfilNombre) {
        this.perfilNombre = perfilNombre;
    }

    public Integer getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }
}
