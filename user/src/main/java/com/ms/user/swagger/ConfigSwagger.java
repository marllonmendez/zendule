package com.ms.user.swagger;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Service")
                        .description("Documentação do User Microservice")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("marllonmendez")
                                .url("https://marllonmendez.vercel.app/pt-br")
                                .email("marlonmendesor@gmail.com")
                        )
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8081")
                                .description("local server")
                        )
                )
                .addTagsItem(new Tag().name("user"));
    }

}