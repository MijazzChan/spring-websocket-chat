package com.zstu.mijazz.storage;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 14-Jun-20.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Storage and Service instance
 */

@Service
public class FileStorage {

    private static final Logger logger = LoggerFactory.getLogger(FileStorage.class);

    private final Path fileStorageLocation;

    @Autowired
    public FileStorage() {
        this.fileStorageLocation = Paths.get("./files").toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception exception) {
            logger.warn("Could create directories when upload hit");
        }
    }

    public String storeFile(MultipartFile multipartFile) {
        String originFileName = multipartFile.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().replace("-", "") + originFileName.substring(originFileName.lastIndexOf("."));

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..") || originFileName.contains("..")) {
                logger.warn("Illegal File Name found, original file name-> {}, random file name-> {}", originFileName, fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception exception) {
            logger.warn("Could not store file");
        }
        return "";
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            logger.warn("Could not fetch file of filename-> {}", fileName);
        }
        return null;
    }
}
