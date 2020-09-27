package com.heq.comp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
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
    /**
     * 调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
     * 通过 createRestApi函数来构建一个DocketBean
     * 如果某个接口不想暴露,可以使用@ApiIgnore注解
     */

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.heq.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        Contact contact = new Contact("heq", "https://github.com/he1253312582/", "1253312582@qq.com");
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 测试使用 Swagger2 构建REST Full API ")
                //创建人
                .contact(contact)
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}
