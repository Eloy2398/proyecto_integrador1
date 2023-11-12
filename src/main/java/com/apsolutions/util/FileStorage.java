package com.apsolutions.util;

import com.apsolutions.config.StoragePathProperties;
import com.apsolutions.exception.CsException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorage {

    private final StoragePathProperties storagePathProperties;

    public FileStorage(StoragePathProperties storagePathProperties) {
        this.storagePathProperties = storagePathProperties;
    }

    private String uploadExec(MultipartFile file, String dir, String filename) {
        String uploadedFilename = "";

        if (file != null && !file.isEmpty()) {
            Path storagePath = Paths.get(storagePathProperties.getImageUploadDir() + "//" + dir);
            String absoluteStoragePath = storagePath.toFile().getAbsolutePath();

            checkIfExistsDir(absoluteStoragePath);

            try {
                if (filename.isEmpty()) {
                    UUID uuid = UUID.randomUUID();
                    uploadedFilename = uuid.toString().replace("-", "");
                } else {
                    uploadedFilename = filename.substring(0, filename.lastIndexOf("."));
                }

                String extensionDot = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
                uploadedFilename += extensionDot;

                byte[] bytesImg = file.getBytes();
                Path fullStorePath = Paths.get(absoluteStoragePath + "//" + uploadedFilename);
                Files.write(fullStorePath, bytesImg);
            } catch (IOException e) {
                throw new CsException("Upload error: " + e.getMessage());
                //throw new RuntimeException(e);
            }
        }

        return uploadedFilename;
    }

    public String upload(MultipartFile file, String dir) {
        return uploadExec(file, dir, "");
    }

    public String upload(MultipartFile file, String dir, String filename) {
        return uploadExec(file, dir, filename);
    }

    private void checkIfExistsDir(String absolutePath) {
        File dir = new File(absolutePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new CsException("Error creating file directory " + absolutePath);
            }
        }
    }

    public Resource download(String filename, String dir) {
        Path fileStorageLocation = Paths.get(storagePathProperties.getImageUploadDir() + "//" + dir).resolve(filename);

        try {
            Resource resource = new UrlResource(fileStorageLocation.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new CsException("The file could not be found or cannot be read");
            }
        } catch (MalformedURLException e) {
            throw new CsException("Download error: " + e.getMessage());
            //throw new RuntimeException(e);
        }
    }

    public boolean delete(String filename, String dir) {
        if (filename != null && !filename.isEmpty()) {
            Path fileStorageLocation = Paths.get(storagePathProperties.getImageUploadDir() + "//" + dir).resolve(filename);

            try {
                return Files.deleteIfExists(fileStorageLocation);
            } catch (IOException e) {
                throw new CsException("Error deleting file");
                //throw new RuntimeException(e);
            }
        }

        return true;
    }

    public String getFullFilePath(String filename, String dir) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(storagePathProperties.getImageUploadDir() + "//" + dir + "//").path(filename).toUriString();
    }
}
