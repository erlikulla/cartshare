package com.cartshare.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // This applies the rule to every single endpoint in your app
                .allowedOrigins("http://localhost:5173") // Your specific React frontend address
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allows all standard web actions
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}