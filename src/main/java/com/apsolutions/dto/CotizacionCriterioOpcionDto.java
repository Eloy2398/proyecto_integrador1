package com.apsolutions.dto;

public class CotizacionCriterioOpcionDto {
    private String descripcion;

    private String criterio;

    public CotizacionCriterioOpcionDto(String descripcion, String criterio) {
        this.descripcion = descripcion;
        this.criterio = criterio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
}
