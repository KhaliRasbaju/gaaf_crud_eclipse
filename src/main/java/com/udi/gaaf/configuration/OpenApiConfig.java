package com.udi.gaaf.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
    public OpenAPI inventarioOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server()
                        .url("http://localhost:8080/api/inventario")
                        .description("Gateway Inventario"))
                .info(new Info()
                        .title("API de Inventario")
                        .version("1.0.0")
                        .description("Microservicio encargado de la gestión de productos e inventarios.")
                        .contact(new Contact()
                                .name("Semillero UDI")));
    }
}
