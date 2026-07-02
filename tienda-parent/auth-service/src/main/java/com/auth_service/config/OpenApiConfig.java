package com.auth_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Auth Service API",
                version = "1.0.0",
                description = "Microservicio de autenticacion y gestion de usuarios de seguridad"
        )
)
public class OpenApiConfig {
}
