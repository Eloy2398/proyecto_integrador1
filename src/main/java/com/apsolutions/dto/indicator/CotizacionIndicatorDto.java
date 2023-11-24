package com.apsolutions.dto.indicator;

public class CotizacionIndicatorDto {
    private String datename;
    private Long number;

    public CotizacionIndicatorDto(String datename, Long number) {
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
