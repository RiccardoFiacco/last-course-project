package com.example.demo.final_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll() // o limitare se serve
                .requestMatchers(HttpMethod.GET, "/admin/*/", "/admin/*/show").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/admin/**").hasAuthority("ADMIN")// tutto quello che inizia con admin e finisce con
                                                                   // create è solo per admin
                .requestMatchers("/", "/index.html", "/static/**").permitAll() //
                .anyRequest().authenticated()
                .and().logout()
                .and().exceptionHandling()
                .and().formLogin();

        return http.build();
    }

    // AuthProvider
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // questo provider usera x come servizio per recuperare gli utenti via username
        authProvider.setUserDetailsService(userDetailsService());
        // e passwordEncoder per codificare le password
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }

    @Bean // Crea e registra un bean di tipo DatabaseUserDetailService nel contesto Spring
    DataBaseUserDetailService userDetailsService() {
        // bean usato da Spring Security come UserDetailsService
        // per recuperare le informazioni dell’utente dal database, quando qualcuno
        // effettua il login.
        return new DataBaseUserDetailService();
    }

    // Password Encoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
