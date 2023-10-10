package com.apsolutions.dto;

public class ClienteDto {
    private Integer id;
    private String nombre;
    private String documento;
    private String tipodocumento;
    private String telefono;
    private String direccion;
    private String email;
    private Integer idcliente;
    private Boolean estado;

    public ClienteDto(Integer id, String nombre, String documento, String tipodocumento, String telefono, String direccion, String email, Integer idcliente, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.tipodocumento = tipodocumento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.idcliente = idcliente;
        this.estado = estado;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
