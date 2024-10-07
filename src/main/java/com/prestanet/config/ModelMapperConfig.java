package com.prestanet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://prestamos-insoii.onrender.com", "http://localhost:4200")  // Permitir acceso solo desde estos orígenes
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")  // Métodos permitidos
                .allowedHeaders("*")  // Permitir todos los encabezados (recomendado para pruebas)
                .exposedHeaders("Authorization", "Content-Type")  // Cabeceras que pueden ser leídas en la respuesta
                .allowCredentials(true)  // Permitir cookies y autenticación
                .maxAge(3600);  // Cache de la preflight en segundos
    }
}
