package com.wangchenglong.myblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 19:45
 * @Description: TODO
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {
    @Bean
    public Docket adminApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                .groupName("后台管理端接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wangchenglong.myblog.controller.admin"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder().title("个人博客-管理端相关接口")
                .description("个人博客后台管理api")
                .contact(new Contact("王成龙", "", "1376834589@qq.com"))
                .version("1.0").build();
    }
    @Bean
    public Docket blogApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(blogApiInfo())
                .groupName("博客呈现相关接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wangchenglong.myblog.controller.api"))
                .paths(PathSelectors.any())
                .build();
                return docket;
    }

    private ApiInfo blogApiInfo(){
        return new ApiInfoBuilder().title("个人博客-博客端接口")
                .description("博客后台管理api")
                .contact(new Contact("王成龙", "", "1376834589@qq.com"))
                .version("1.0").build();
    }
}