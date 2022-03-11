package org.generation.BlogPessoal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto Blog Pessoal")
                        .description("Projeto para postagens de diversos temas")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("Github Gabriel Melim")
                                .url("https://github.com/GabrielMelim")
                                .email("gabrielmelim2012@hotmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github Project")
                        .url("https://github.com/gabrielmelim/GenerationBrasilBlogPessoal"));
    }

}
