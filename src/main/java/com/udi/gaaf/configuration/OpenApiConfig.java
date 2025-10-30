package com.udi.gaaf.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para la documentación de la API mediante OpenAPI (Swagger).
 * 
 * <p>Esta clase define la información general del microservicio de inventario, 
 * incluyendo su título, descripción, versión, contacto y servidor base.</p>
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura y construye la documentación OpenAPI del microservicio de inventario.
     * 
     * <p>Define los metadatos principales, como el título de la API, su descripción,
     * la versión actual y la información de contacto. Además, especifica el servidor base
     * donde se expone la API a través del gateway.</p>
     * 
     * @return un objeto {@link OpenAPI} configurado con la información del microservicio.
     */
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
