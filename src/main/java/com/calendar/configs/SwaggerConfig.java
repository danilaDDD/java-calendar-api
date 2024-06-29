package com.calendar.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(globalParameterList())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.calendar"))
                .build()
                .apiInfo(apiInfo());


    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API для хранения событий")
                .build();
    }

    private List<Parameter> globalParameterList() {

        Parameter authTokenHeader =
                new ParameterBuilder()
                        .name("Bearer токен авторизации") // name of the header
                        .modelRef(new ModelRef("string")) // data-type of the header
                        .required(false) // required/optional
                        .parameterType("header") // for query-param, this value can be 'query'
                        .description("Токен авторизации, сгенерированного от пользователя или в разделе авторизации по токену. " +
                                "Например: Bearer tegegyueuuewyudwyuguedywu")
                        .build();

        return Collections.singletonList(authTokenHeader);
    }
}


