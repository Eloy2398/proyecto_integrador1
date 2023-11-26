package com.apsolutions.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class JasperReportGenerator {

    private static final String RESOURCE_LOCATION = "templates";
    private static final String EXTENSION = ".jasper";

    public byte[] generatePdf(Map<String, Object> params, String reportName) {
        return fnGeneratePdf(params, reportName);
    }

    public byte[] generateExcel(Map<String, Object> params, String reportName) {
        return fnGeneratePdf(params, reportName);
    }

    private byte[] fnGeneratePdf(Map<String, Object> params, String reportName) {
        try {
            return JasperExportManager.exportReportToPdf(getReport(params, reportName));
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] fnGenerateExcel(Map<String, Object> params, String reportName) throws JRException, IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(stream);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(params, reportName)));
        exporter.setExporterOutput(output);

        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(true);

        exporter.setConfiguration(configuration);
        exporter.exportReport();

        output.close();
        return stream.toByteArray();
    }

    private JasperPrint getReport(Map<String, Object> params, String reportName) throws IOException, JRException {
        ClassPathResource resource = new ClassPathResource(RESOURCE_LOCATION + File.separator + reportName + EXTENSION);
        return JasperFillManager.fillReport(resource.getInputStream(), params, new JREmptyDataSource());
    }
}
