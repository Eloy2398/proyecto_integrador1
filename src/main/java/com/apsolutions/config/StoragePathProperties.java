package com.apsolutions.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "storage.path")
public class StoragePathProperties {

    private String imageUploadDir;
    private String fileUploadDir;

    public String getImageUploadDir() {
        return imageUploadDir;
    }

    public void setImageUploadDir(String imageUploadDir) {
        this.imageUploadDir = imageUploadDir;
    }

    public String getFileUploadDir() {
        return fileUploadDir;
    }

    public void setFileUploadDir(String fileUploadDir) {
        this.fileUploadDir = fileUploadDir;
    }
}
