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

/**
 * configuration indica che questa classe è una configurazione di Spring
 * inoltre vado a dire a spring di usare queste configurazioni per ogni
 * richiesta web
 * 
 * @return
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * quando Spring esegue l'applicazione, si occupa della creazione,
     * configurazione e gestione di questo oggetto.
     * con suppreswarnings andiamo a dire a java di non darci avvisi per l'uso di
     * classi deprecate
     * @return
     */
    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll() // tutti gli endpoint che iniziano con api sono accessibili a tutti
                .requestMatchers(HttpMethod.GET, "/admin/*/", "/admin/*/show").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/", "/index.html", "/static/**").permitAll() // tutti gli altri endpoint sono accessibili a tutti
                .anyRequest().authenticated()
                .and().logout()
                .and().formLogin();

        return http.build();
    }

    /**
     * l'annotation dice che il metodo crea un oggetto che sarà gestito da Spring
     * come un bean.
     * quando Spring esegue l'applicazione, si occupa della creazione,
     * configurazione e gestione di questo oggetto.
     * Spring registrerà l'oggetto DaoAuthenticationProvider restituito dal metodo
     * come un bean
     * che utilizzera poi, in base alle necessita, nell'applicazione.
     * 
     * @return
     */
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * l'annotation dice che il metodo restituira un oggetto che sara gestito da
     * spring come un bean
     * Spring chiama questo metodo all'avvio, prende l'oggetto creato e lo mette nel
     * suo contesto
     * 
     * @return
     */
    @Bean
    DataBaseUserDetailService userDetailsService() {
        return new DataBaseUserDetailService();
    }

    /**
     * stessa cosa di sopra, ma ritorna un oggetto PasswordEncoder
     * che ci aiuta nella codifica delle password
     * 
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
