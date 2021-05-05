package com.cping.project.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("Http://localhost:8080", "null")
                .allowedMethods("GET", "PUT", "POST", "OPTIONS", "DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
