package com.apsolutions.dto;

import com.apsolutions.model.Criterio;
import jakarta.persistence.*;

public class CriterioopcionDto {

    private Integer id;

    private String descripcion;

    private CriterioDto criterio; // Muchos criterios de opcion pueden estar en un criterio

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CriterioDto getCriterio() {
        return criterio;
    }

    public void setCriterio(CriterioDto criterio) {
        this.criterio = criterio;
    }
}
