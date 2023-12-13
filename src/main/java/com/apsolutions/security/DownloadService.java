package com.apsolutions.security;

import com.apsolutions.config.StoragePathProperties;
import com.apsolutions.dto.JasperReportDto;
import com.apsolutions.repository.CotizacionRepository;
import com.apsolutions.repository.CotizaciondetalleRepository;
import com.apsolutions.util.Encryptor;
import com.apsolutions.util.FileStorage;
import com.apsolutions.util.JasperReportGenerator;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class DownloadService {

    @Autowired
    private FileStorage fileStorage;
    @Autowired
    private JasperReportGenerator jasperReportGenerator;
    @Autowired
    private CotizacionRepository cotizacionRepository;
    @Autowired
    private CotizaciondetalleRepository cotizaciondetalleRepository;
    @Autowired
    private StoragePathProperties properties;

    public ResponseEntity<Resource> download(String filename, String dir) {
        Resource file = fileStorage.download(filename, dir);
        try {
            String contentType = Files.probeContentType(file.getFile().toPath());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Resource> downloadPdf(String code) {
        try {
            int id = Integer.parseInt(Encryptor.decrypt(code));

            InputStream logo = new FileInputStream(new File(Paths.get(properties.getImageUploadDir() + "//company").resolve("logo.png").toUri()));
            Map<String, Object> params = new HashMap<>();
            params.put("companyRuc", "10776657773");
            params.put("companyName", "Ap Solutions");
            params.put("companyAddress", "Los Pinos de la Plata, Chiclayo, Peru");
            params.put("companyLogo", logo);
            params.put("quoteNumber", String.valueOf(id));
            params.put("date", new SimpleDateFormat("dd-MM-yyyy").format(cotizacionRepository.getDate(id)));
            params.put("dts", new JRBeanCollectionDataSource(cotizaciondetalleRepository.listByIdCotizacionSimplifado(id)));

            JasperReportDto jasperReportDto = jasperReportGenerator.generatePdf("cotizacionPdf", params);

            return ResponseEntity.ok().headers(jasperReportDto.getHeaders()).contentLength(jasperReportDto.getLength()).body(jasperReportDto.getResource());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
