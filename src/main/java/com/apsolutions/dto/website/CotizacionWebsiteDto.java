package com.apsolutions.dto.website;

public class CotizacionWebsiteDto {
    private String nombre;
    private String email;
    private String criterioopcionList;
    private String productoList;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCriterioopcionList() {
        return criterioopcionList;
    }

    public void setCriterioopcionList(String criterioopcionList) {
        this.criterioopcionList = criterioopcionList;
    }

    public String getProductoList() {
        return productoList;
    }

    public void setProductoList(String productoList) {
        this.productoList = productoList;
    }
}
