package com.finalexample.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {

    @Value("${app.image.upload.path}")
    private String IMAGE_FOLDER_PATH;

    @Value("${app.image.upload.handler}")
    private String IMAGE_BASE_ENDPOINT;

    public String saveImage(MultipartFile multipartFile, Long carListingId) throws IOException {
        String fileName = "";
        Path uploadPath = Paths.get(String.format("%s/%s", IMAGE_FOLDER_PATH, carListingId));

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            fileName = getFileName(multipartFile.getOriginalFilename());
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + multipartFile.getOriginalFilename(), ioe);
        }

        return fileName;
    }

    public String getImagePath(long id, String imageName) {
        return String.format("/%s/%d/%s", IMAGE_BASE_ENDPOINT, id, imageName);
    }

    private String getFileName(String originalFileName) {
        return originalFileName.trim().replace(" ", "_").toLowerCase();
    }
}
