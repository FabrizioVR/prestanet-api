package com.prestanet.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ModelMapperConfig implements WebMvcConfigurer {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:4200",  // Frontend local
                    "https://prestamos-insoii.onrender.com",
                    "https://prestamos-insoii.onrender.com/login"  // Agregue el dominio actual para permitir el acceso
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Incluye OPTIONS para preflight requests
                .allowedHeaders("Content-Type", "Authorization", "Access-Control-Allow-Origin")  // Especifica encabezados permitidos
                .exposedHeaders("Authorization")  // Permite que el frontend acceda a la cabecera 'Authorization' si es necesario
                .allowCredentials(true)  // Habilita envío de credenciales (por ejemplo, cookies)
                .maxAge(3600);  // Especifica el tiempo en segundos que los resultados de las solicitudes preflight se pueden almacenar en caché
    }
}
