package com.example.Laptop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Configuracion Swagger para la generacion de documentacion de la API REST
 *
 * http://localhost:8081/swagger-ui/
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket petApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiDetails(){
        return new ApiInfo("SpringBoot API REST Laptops",
                "IT shop API REST docs",
                "v1.0",
                "http://www.google.com",
                new Contact("Gabriel", "http://www.google.com", "gabri@xmail.com"),
                "MIT",
                "http://www.google.com", Collections.emptyList());
    }
}
