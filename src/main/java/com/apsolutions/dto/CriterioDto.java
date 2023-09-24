package com.apsolutions.dto;

import java.util.ArrayList;
import java.util.List;

public class CriterioDto {

    private Integer id;

    private String nombre;

    private Boolean estado;

    private List<CriterioopcionDto> criterioopcionList = new ArrayList<>();

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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<CriterioopcionDto> getCriterioopcionList() {
        return criterioopcionList;
    }

    public void setCriterioopcionList(List<CriterioopcionDto> criterioopcionList) {
        this.criterioopcionList = criterioopcionList;
    }
}
