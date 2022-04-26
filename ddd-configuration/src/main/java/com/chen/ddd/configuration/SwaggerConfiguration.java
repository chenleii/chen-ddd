package com.chen.ddd.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cl
 * @version 1.0
 * @since 2020/11/23 21:03
 */
@ConditionalOnProperty(prefix = "api.doc", name = "enabled", havingValue = "true")
@Configuration
//@EnableSwagger2
//@EnableKnife4j
public class SwaggerConfiguration {

//    @Bean
//    public Docket restApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.chen.ddd.interfaces.http"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("应用接口文档")
//                .description("应用接口文档")
//                .version("1.0")
//                .build();
//    }


    @Bean
    public GroupedOpenApi userApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"com.chen.ddd.interfaces.http"};
        return GroupedOpenApi.builder()
                .group("应用接口文档")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch)
                .build();
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("应用接口文档")
                                .description("应用接口文档")
                                .version("1.0.0")
                                .termsOfService("https://example.com/terms/")
                                .license(
                                        new License()
                                                .name("Apache 2.0")
                                                .identifier("Apache-2.0")
                                )
                                .contact(
                                        new Contact()
                                                .name("API Support")
                                                .url("https://www.example.com/support")
                                                .email("chenleinxd@gamil.com")
                                )
                );
    }
}
