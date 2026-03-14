package com.example.skunivProject.global.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://*.ngrok-free.dev", // ngrok 모든 URL 자동 허용
                        "https://nonsegmentary-sharmaine-puerile.ngrok-free.dev",
                        "https://designative-pricily-rosy.ngrok-free.dev",
                        "http://localhost:3000",
                        "http://localhost:5173"// 로컬 프론트
                )
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
