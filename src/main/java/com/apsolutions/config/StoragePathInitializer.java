package com.apsolutions.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class StoragePathInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final StoragePathProperties storagePathProperties;

    public StoragePathInitializer(StoragePathProperties storagePathProperties) {
        this.storagePathProperties = storagePathProperties;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createStoragePath(storagePathProperties.getImageUploadDir());
        createStoragePath(storagePathProperties.getFileUploadDir());
    }

    private void createStoragePath(String storagePath) {
        File dir = new File(storagePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Can not create storage folder " + storagePath);
            }
        }
    }
}
