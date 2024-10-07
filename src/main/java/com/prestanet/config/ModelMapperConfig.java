package com.prestanet.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ModelMapperConfig implements WebMvcConfigurer {

    // Bean de ModelMapper para facilitar la conversión entre DTOs y entidades.
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Configuración global de CORS para permitir acceso controlado a la API desde diferentes orígenes.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:4200",  // Origen del frontend local para desarrollo
                    "https://prestamos-insoii.onrender.com"  // Origen de la aplicación desplegada en producción
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permitir todos los encabezados
                .exposedHeaders("Authorization")  // Cabeceras expuestas que se pueden leer desde el cliente
                .allowCredentials(true)  // Habilitar el uso de cookies y credenciales en las solicitudes CORS
                .maxAge(3600);  // Cache de preflight (CORS OPTIONS) en segundos
    }
}
