package com.foodsaver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val corsConfigurationSource: CorsConfigurationSource
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.configurationSource(corsConfigurationSource) }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth ->
                auth
                    // Usar AntPathRequestMatcher explícitamente
                    .requestMatchers(
                        AntPathRequestMatcher("/api/auth/**"),
                        AntPathRequestMatcher("/api/health"),
                        AntPathRequestMatcher("/api/ping"),
                        AntPathRequestMatcher("/api/products/test"),
                        AntPathRequestMatcher("/api/users/test"),
                        AntPathRequestMatcher("/h2-console/**"),
                        AntPathRequestMatcher("/error"),
                        AntPathRequestMatcher("/favicon.ico")
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .headers { headers -> 
                headers.frameOptions().sameOrigin() // Para H2 Console
            }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}