package com.retailer.ordermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Profile({"dev","prod"})
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        //.requestMatchers("/swagger-ui/**", "/api-docs/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/**").authenticated() // Ensure all authenticated users can access
                        .anyRequest().permitAll()
                )
                // Disable CSRF for Swagger-related paths
                //.csrf(csrf -> csrf.ignoringRequestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**"))
                .httpBasic(withDefaults());
        return http.build();
    }
}