package com.calendar.configs;


import io.swagger.annotations.AuthorizationScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.calendar"))
                .build()
                .apiInfo(metaData());

    }

    protected ApiInfo metaData(){
        return new ApiInfo(
                "Events calendar api",
                "Api for saving events",
                "1.0.0",
                "Terms of service",
                 null,
                "Licence of API",
                ""
        );
    }
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
}


