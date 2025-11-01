package com.foodsaver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfig {

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        
        // Usar allowedOriginPatterns en lugar de allowedOrigins
        configuration.allowedOriginPatterns = listOf(
            "http://localhost:*",
            "http://127.0.0.1:*", 
            "http://10.0.2.2:*",    // Android emulator
            "http://192.168.*.*:*"   // Red local
        )
        
        configuration.allowedMethods = listOf(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        )
        
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        configuration.maxAge = 3600L
        
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}