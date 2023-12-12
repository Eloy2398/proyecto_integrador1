package com.apsolutions.security;

import com.apsolutions.dto.JasperReportDto;
import com.apsolutions.util.Encryptor;
import com.apsolutions.util.FileStorage;
import com.apsolutions.util.JasperReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class DownloadService {

    @Autowired
    private FileStorage fileStorage;
    @Autowired
    private JasperReportGenerator jasperReportGenerator;

    public ResponseEntity<Resource> download(String filename, String dir) {
        Resource file = fileStorage.download(filename, dir);
        String contentType = null;
        try {
            contentType = Files.probeContentType(file.getFile().toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }

    public ResponseEntity<Resource> downloadPdf(String code) {

        int id = Integer.parseInt(Encryptor.decryptFromUri(code));
        System.out.println(id);

        JasperReportDto jasperReportDto = jasperReportGenerator.generatePdf("cotizacionPdf", null);

        return ResponseEntity.ok().headers(jasperReportDto.getHeaders()).contentLength(jasperReportDto.getLength()).body(jasperReportDto.getResource());
    }
}
