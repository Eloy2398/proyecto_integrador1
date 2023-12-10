package com.apsolutions.dto.website;

import com.apsolutions.dto.CriterioopcionDto;

import java.util.List;

public class CriterioWebsiteDto {
    private Integer id;
    private String nombre;
    private List<CriterioopcionDto> criterioopcionDtoList;

    public CriterioWebsiteDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<CriterioopcionDto> getCriterioopcionDtoList() {
        return criterioopcionDtoList;
    }

    public void setCriterioopcionDtoList(List<CriterioopcionDto> criterioopcionDtoList) {
        this.criterioopcionDtoList = criterioopcionDtoList;
    }
}
