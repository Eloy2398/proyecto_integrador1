package com.apsolutions.dto;

public class IndicadorCotizacionDto {
    private String datename;
    private Long number;

    public IndicadorCotizacionDto(String datename, Long number) {
        this.datename = datename;
        this.number = number;
    }

    public String getDatename() {
        return datename;
    }

    public Long getNumber() {
        return number;
    }
}
