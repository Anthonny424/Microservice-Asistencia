package com.microservice.alumnos.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IStorage {
    void init() throws IOException;
    String store(MultipartFile file);
    String store(byte[] content, String filename);
    Resource loadAsResource(String filename);

}
