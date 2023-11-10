package com.ssafy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String API_NAME = "Study API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "Study API 명세서";
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.consumes(getConsumeContentTypes())
        		.produces(getProduceContentTypes())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo(
				"Enjoy-trip TEST API",
				"테스트를 위한 test api 작성중",
				"0.0.1",
				"Terms of Service",
				new Contact("SSAFY 10TH-DAJEON-5",
						"www.ssafy.com", "denny10002@naver.com"),
				"License of API", "edu.ssafy.com",
				Collections.emptyList());
	}
	
	private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
}
