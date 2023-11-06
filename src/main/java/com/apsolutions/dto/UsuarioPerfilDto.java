package com.apsolutions.dto;

public class UsuarioPerfilDto {

    private String usuario;

    private String claveAnterior;

    private String claveNueva;

    public UsuarioPerfilDto(String usuario, String claveAnterior, String claveNueva) {
        this.usuario = usuario;
        this.claveAnterior = claveAnterior;
        this.claveNueva = claveNueva;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClaveAnterior() {
        return claveAnterior;
    }

    public void setClaveAnterior(String claveAnterior) {
        this.claveAnterior = claveAnterior;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }
}
