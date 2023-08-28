package br.com.incode.demodocker.application.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SpringDocConfigurations {

    private final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API Documentation")
                        .description("Rest API created by Matheus")
                        .version("1.0.0")
                        .license(new io.swagger.v3.oas.models.info.License().name("InCode, all rights reserved")
                                .url("")))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")))
                .servers(List.of(
                        new Server().url("http://localhost:9999/demodocker"),
                        new Server().url("http://localhost:8080/demodocker")
                ));
    }
}
