package com.microservice.alumnos.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements IStorage {

    @Value("${media.location}")
    private String mediaFolder;

    private Path rootLocation;

    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get("").toAbsolutePath().resolve(mediaFolder).normalize();
        Files.createDirectories(rootLocation);
    }



    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Error, archivo vacío");
            }
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path destinationFile = rootLocation.resolve(filename).normalize();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo: " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public String store(byte[] content, String filename) {
        try {
            Path destinationFile = rootLocation.resolve(filename).normalize();
            Files.write(destinationFile, content);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo: " + filename, e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se pudo leer el archivo: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al cargar el archivo: " + filename, e);
        }
    }
}


