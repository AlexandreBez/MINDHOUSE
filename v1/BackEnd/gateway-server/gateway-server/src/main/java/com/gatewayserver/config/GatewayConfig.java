package com.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gatewayserver.cors.CorsFilter;

@Configuration
public class GatewayConfig {

    @Bean
    CorsFilter corsFilter() {
        return new CorsFilter();
    }
    
}
