package com.exacom.proyectofinal.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(new Components())
                .info(
                        new Info()
                                .title("Proyecto Final")
                                .description("Proyecto Final de Exacom IT")
                                .version("v1.0")
                );
    }
}
