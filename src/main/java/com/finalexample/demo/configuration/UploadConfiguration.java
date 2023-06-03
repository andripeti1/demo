package com.finalexample.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class UploadConfiguration implements WebMvcConfigurer {

    @Value("${app.image.upload.path}")
    private String IMAGE_FOLDER_PATH;

    @Value("${app.image.upload.handler}")
    private String IMAGE_HANDLER;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(IMAGE_FOLDER_PATH, registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
        registry.addResourceHandler(String.format("/%s/**", IMAGE_HANDLER)).addResourceLocations("file:"+ uploadPath + "/");
    }

}
