package dev.hfish.springboot.booktrackerlite.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(configurer -> {
                    configurer.anyRequest().authenticated();
                })
                .oauth2Login(Customizer.withDefaults())
                // stub until custom authorization is implemented
                // .formLogin(Customizer.withDefaults())
                .build();
    }
}
