package com.example.mqq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

@Bean
public Docket createRestApi() {

    return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .enable(true)
            .securitySchemes(Collections.singletonList(HttpAuthenticationScheme.JWT_BEARER_BUILDER
//                        显示用
                    .name("Authorization")
                    .build()))
            .securityContexts(Collections.singletonList(SecurityContext.builder()
                    .securityReferences(Collections.singletonList(SecurityReference.builder()
                            .scopes(new AuthorizationScope[0])
                            .reference("Authorization")
                            .build()))
                    // 声明作用域
                    .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                    .build()))
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(this.getParameterList());
}

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3-Demo接口文档")
                .description("信息技术交流平台")
                .version("1.0")
                .build();
    }

    private List<Parameter> getParameterList() {
        ParameterBuilder clientIdTicket = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        clientIdTicket.name("Authorization").description("token令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build(); //设置false，表示clientId参数 非必填,可传可不传！
        pars.add(clientIdTicket.build());

        return pars;

    }


}
