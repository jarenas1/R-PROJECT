package com.riwi_project.Riwi_project.configuration.swaggerConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "riwi project",
                description = "descripciónnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn",
                termsOfService = "url de términos y condiciones",
                version = "1.0.0", //versión
                contact = @Contact( //información de creador
                        name = "mi nombre",
                        url = "pagina de contacto",
                        email = "mi email"
                )
        ),
        servers = { //configuramos los servers
                @Server(
                        description = "Nombre server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Server product",
                        url = "https:aksaks.com"
                )
        },
        security = @SecurityRequirement(
                name = "seguridadDeJuan" //PONEMOS EL MISMO NOMBRE DE ABAJO
        )
)
@SecurityScheme(
        name = "seguridadDeJuan",
        description="descriptiiioooon",
        type = SecuritySchemeType.HTTP, //HTTP se trabaja con token
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER, //donde va el token,
        scheme = "bearer",
        bearerFormat = "JWT"
)

public class SwaggerCinfig {
}
