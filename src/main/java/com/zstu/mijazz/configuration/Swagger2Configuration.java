package com.zstu.mijazz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 10-Jun-20.
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    public static final String SCAN_BASE = "com.zstu.mijazz";

    public static final String VERSION = "0.0.1";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SCAN_BASE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("IMS Service Apis")
                .description("Api Document")
                .version(VERSION)
                .build();
    }
}
