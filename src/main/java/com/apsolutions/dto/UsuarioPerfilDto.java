package com.apsolutions.dto;

public class UsuarioPerfilDto {

    private String usuario;

    private String claveActual;

    private String claveNueva;

    public UsuarioPerfilDto(String usuario, String claveAnterior, String claveNueva) {
        this.usuario = usuario;
        this.claveActual = claveAnterior;
        this.claveNueva = claveNueva;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveAnterior) {
        this.claveActual = claveAnterior;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }
}
