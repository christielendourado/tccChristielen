package com.christielen.informacoescadastrais.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "informacoescadastrais",
                version = "v1.0"
        ),
        servers = @Server(url = "http://informacoescadastrais:8080")
)
public class OpenAPIConfig {
}
