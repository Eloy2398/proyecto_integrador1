package com.apsolutions.dto;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

public class JasperReportDto {

    private HttpHeaders headers;
    private int length;
    private Resource resource;

    public JasperReportDto(HttpHeaders headers, int length, Resource resource) {
        this.headers = headers;
        this.length = length;
        this.resource = resource;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public int getLength() {
        return length;
    }

    public Resource getResource() {
        return resource;
    }
}
