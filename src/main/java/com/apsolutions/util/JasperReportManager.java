package com.apsolutions.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class JasperReportManager {

    private static final String RESOURCE_LOCATION = "templates";
    private static final String EXTENSION = ".jasper";

    public byte[] generatePdf(String reportName, Map<String, Object> params) {
        return fnGeneratePdf(reportName, params);
    }

    public byte[] generateExcel(String reportName, Map<String, Object> params) {
        return fnGenerateExcel(reportName, params);
    }

    private byte[] fnGeneratePdf(String reportName, Map<String, Object> params) {
        try {
            return JasperExportManager.exportReportToPdf(getReport(params, reportName));
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] fnGenerateExcel(String reportName, Map<String, Object> params) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(stream);
        JRXlsxExporter exporter = new JRXlsxExporter();

        try {
            exporter.setExporterInput(new SimpleExporterInput(getReport(params, reportName)));
            exporter.setExporterOutput(output);

            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(true);
            exporter.setConfiguration(configuration);
            exporter.exportReport();

            output.close();
            return stream.toByteArray();
        } catch (IOException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    private JasperPrint getReport(Map<String, Object> params, String reportName) throws IOException, JRException {
        ClassPathResource resource = new ClassPathResource(RESOURCE_LOCATION + File.separator + reportName + EXTENSION);
        return JasperFillManager.fillReport(resource.getInputStream(), params, new JREmptyDataSource());
    }
}
