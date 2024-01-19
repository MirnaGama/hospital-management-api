package com.mirna.hospitalmanagementapi.infra.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIDocConfiguration {

	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                       .components(new Components()
                           .addSecuritySchemes("bearer-key",
                           new SecurityScheme().type(SecurityScheme.Type.HTTP)
                           .scheme("bearer").bearerFormat("JWT")))
                       .info(new Info()
                               .title("Hospital Management API")
                               .description("Rest API of the Hospital Management application. It contains CRUD functionalities for doctors and patients, as well as scheduling and canceling consultations")
                               .contact(new Contact()
                                       .name("Developer: Mirna Gama")
                                       .url("https://github.com/MirnaGama")));
   }
}
