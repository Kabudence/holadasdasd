package com.github.inncontrol.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;

import java.util.Collections;

@Configuration
public class OpenApiConfiguration {

    @Value("${spring.profiles.default}")
    private String activeProfile;
    
    @Bean
    public OpenAPI learningPlatformOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi.info(new Info()
                        .title("Inn Control Platform API")
                        .description("Inn Control application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Inn Control platform wiki documentation")
                        .url("https://inncontrol-platform.wiki.github.io/docs"));

         boolean isRunningInProd =  activeProfile.equals("prod");

        if (isRunningInProd) {
            openApi.servers(Collections.singletonList(new Server().url("https://inncontrol-api.ryzeon.me")));
        } else {
            openApi.servers(Collections.singletonList(new Server().url("http://localhost:8080")));
        }

        final String securitySchemeName = "bearerAuth";

        openApi.addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
        return openApi;
    }
}
