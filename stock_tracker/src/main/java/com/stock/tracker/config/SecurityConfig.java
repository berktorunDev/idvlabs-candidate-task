package com.stock.tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.stock.tracker.enums.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define a Bean for BCryptPasswordEncoder
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define a SecurityFilterChain for configuring security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configure the cors
        http.cors(Customizer.withDefaults());

        // Disable Cross-Site Request Forgery (CSRF) protection
        http.csrf(csrf -> csrf.disable());
        // Authorize all HTTP requests, allowing any request to be permitted
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html")
                .permitAll()
                .requestMatchers("/api/user/**").hasRole(UserRole.NORMAL_USER.toString())
                .requestMatchers("/api/admin/**").hasRole(UserRole.ADMIN.toString())
                .anyRequest().authenticated());

        // Return the configured security filter chain
        return http.build();
    }
}
