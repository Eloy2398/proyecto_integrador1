package com.apsolutions.util;

import com.apsolutions.dto.JasperReportDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JasperReportGenerator {

    private final JasperReportManager jasperReportManager;

    public JasperReportGenerator(JasperReportManager jasperReportManager) {
        this.jasperReportManager = jasperReportManager;
    }

    public JasperReportDto generatePdf(String reportName, Map<String, Object> params) {
        byte[] byteArray = jasperReportManager.generateExcel(reportName, params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(reportName, reportName + ".pdf");
        return new JasperReportDto(headers, byteArray.length, new ByteArrayResource(byteArray));
    }

    public JasperReportDto generateExcel(String reportName, Map<String, Object> params) {
        byte[] byteArray = jasperReportManager.generateExcel(reportName, params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(reportName + ".xlsx").build());
        return new JasperReportDto(headers, byteArray.length, new ByteArrayResource(byteArray));
    }
}
