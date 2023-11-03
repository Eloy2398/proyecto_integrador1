package com.apsolutions.security;

import com.apsolutions.util.FileStorage;
import com.apsolutions.util.Global;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class DownloadService {

    private final FileStorage fileStorage;

    public DownloadService(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public ResponseEntity<Resource> download(String filename) {
        Resource file = fileStorage.download(filename, Global.DIR_PRODUCTS);
        String contentType = null;
        try {
            contentType = Files.probeContentType(file.getFile().toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }
}
