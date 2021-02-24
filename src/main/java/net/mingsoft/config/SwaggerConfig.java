package net.mingsoft.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private Boolean SWAGGER_ENABLE;
    
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).enable(SWAGGER_ENABLE).apiInfo(apiInfo()).select()
//				.apis(RequestHandlerSelectors.basePackage("net.mingsoft.cms.web")).paths(PathSelectors.any())
//				.build();
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("api文档").description("restful 风格接口")
//				// 服务条款网址
//				// .termsOfServiceUrl("")
//				.version("1.0")
//				// .contact(new Contact("hello", "url", "email"))
//				.build();
//	}
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).enable(SWAGGER_ENABLE).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("net.mingsoft.cms.action.web"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("api文档").description("api文档").termsOfServiceUrl("").version("1.0.0").build();
    }
	
}
