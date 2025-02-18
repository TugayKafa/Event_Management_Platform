package com.project.EventManagementPlatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login").anonymous()
                        .requestMatchers("/css/**", "/home", "/places", "/", "/events").permitAll() // Public pages
                        .requestMatchers("/places/add").hasAuthority("ROLE_ADMIN") // Admin only
                        .requestMatchers("/places/{id}").hasAuthority("ROLE_ADMIN") // Admin only
                        .requestMatchers("/logout").authenticated() // Ensure these endpoints are protected
                        .anyRequest().authenticated() // Protect other endpoints
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) // Redirect after successful login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}