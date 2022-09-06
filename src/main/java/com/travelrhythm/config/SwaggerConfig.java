package com.travelrhythm.config;

import com.fasterxml.classmate.TypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfig {

  private final TypeResolver typeResolver;

  @Bean
  public Docket api() {       //Swagger 설정 (Necessary)
    return new Docket(DocumentationType.SWAGGER_2)
        .alternateTypeRules(AlternateTypeRules.newRule(
            typeResolver.resolve(Pageable.class), typeResolver.resolve(Empty.class)))
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.travelrhythm"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {     //문서 설정 (Optional)
    return new ApiInfoBuilder()
        .title("Travel Rhythm API")
        .version("0.1.0")
//                .description("설명 작성")
//                .license("라이센스 작성")
//                .licenseUrl("라이센스 URL 작성")
        .build();
  }

  private class Empty {
  }

}
