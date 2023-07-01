package com.agebra.boonbaebackend.config;

import com.agebra.boonbaebackend.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private String[] whiteList = {
      "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**",
      "/api/v1/users/", "/api/v1/users/login", "/api/v1/comments/{comment_pk}/reports", "/api/v1/recycling/{recycling_pk}/comments",
      "/error"
    };

    private String[] adminList = {
      "/api/v1/recycling_confirm/**","api/v1/qna/{qna_pk}/reply"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(whiteList).permitAll()
                .requestMatchers(adminList).hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
            .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
