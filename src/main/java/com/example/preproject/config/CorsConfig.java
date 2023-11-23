package com.example.preproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 허용할 오리진, 허용할 메서드, 허용할 헤더 등을 설정합니다.
        config.addAllowedOrigin("*"); // 모든 오리진 허용 (필요에 따라 수정)
        config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.setAllowCredentials(true); // Credential 사용 허용 여부

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}