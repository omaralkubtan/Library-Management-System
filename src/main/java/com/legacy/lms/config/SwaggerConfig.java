package com.legacy.lms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        var security = new ArrayList<SecurityRequirement>();

        security.add(new SecurityRequirement().addList("bearerAuth"));

        HashMap<String, SecurityScheme> securitySchemeHashMap = new HashMap<>();

        securitySchemeHashMap.putIfAbsent(
                "bearerAuth",
                new SecurityScheme().scheme("bearer").bearerFormat("JWT").type(SecurityScheme.Type.HTTP)
        );

        return new OpenAPI()
                .components(new Components()
                        .securitySchemes(securitySchemeHashMap)
                )
                .info(new Info()
                        .title("LMS API")
                        .version(appVersion)
                        .contact(new Contact()
                                .name("Omar AL-Kubtan")
                                .email("omarquptan@gmail.com")
                        )
                )
                .security(security);
    }
}
