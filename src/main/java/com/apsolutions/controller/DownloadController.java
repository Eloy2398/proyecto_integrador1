package com.apsolutions.controller;

import com.apsolutions.security.DownloadService;
import com.apsolutions.util.Global;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/download")
public class DownloadController {

    private final DownloadService downloadService;


    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @GetMapping("/producto/{filename}")
    public ResponseEntity<Resource> download_pro(@PathVariable("filename") String filename) {
        return downloadService.download(filename, Global.DIR_PRODUCTS);
    }

    @GetMapping("/categoria/{filename}")
    public ResponseEntity<Resource> download_cat(@PathVariable("filename") String filename) {
        return downloadService.download(filename, Global.DIR_CATEGORIES);
    }

    @GetMapping("/marca/{filename}")
    public ResponseEntity<Resource> download_mar(@PathVariable("filename") String filename) {
        return downloadService.download(filename, Global.DIR_BRANDS);
    }
}
