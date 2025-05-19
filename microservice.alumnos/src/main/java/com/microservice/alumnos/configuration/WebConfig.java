package com.microservice.alumnos.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${media.location}")
    private String mediaLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String mediaPath = Paths.get(mediaLocation).toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/media/**")
                .addResourceLocations(mediaPath);

    }
}
