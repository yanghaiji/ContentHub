package com.javayh.content.hub.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author haiji
 */
@Configuration
public class ContentHubCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/content/hub/api/**")
                        .allowedOrigins("http://localhost:9090")
                        .allowedMethods("GET", "POST", "DELETE")
                        .allowCredentials(true);
            }
        };
    }
}
