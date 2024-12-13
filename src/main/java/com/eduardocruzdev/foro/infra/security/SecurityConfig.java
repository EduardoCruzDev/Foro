package com.eduardocruzdev.foro.infra.security;


import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.List;


import static com.eduardocruzdev.foro.infra.security.AccesRules.*;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final ForoUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CsrfTokenRepository csrfTokenRepository;

    public SecurityConfig(ForoUserDetailsService userDetailsService, PasswordEncoder passwordEncoder, CsrfTokenRepository csrfTokenRepository) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            configureEncodingFilter(http);

            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(FOR_AUTHORIZED_USERS).authenticated()
                            .requestMatchers(FOR_ADMINS).hasAnyAuthority(ADMINS_ROLES) // Cambiado a hasAnyAuthority
                            .requestMatchers(FOR_EVERYONE).permitAll()
                    )
                    .formLogin(login -> login
                            .loginPage("/login")
                            .permitAll()
                    )
                    .logout(LogoutConfigurer::permitAll)
                    .rememberMe(rememberMe -> rememberMe
                            .tokenValiditySeconds(2419200)
                            .key("forum-key")
                    )
                    .csrf(csrf -> csrf
                            .csrfTokenRepository(csrfTokenRepository)
                    );

            return http.build();
        }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    private void configureEncodingFilter(HttpSecurity http) {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.addFilterBefore(filter, CsrfFilter.class);
    }

}
