package com.bcp.app.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.List;

//@Configuration
//@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SpringSecurityConfig {
    private final Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);
    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        return http.authorizeExchange()
                //.pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/api/security/oauth/**", "/api/exchangerate/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/usuarios/usuarios").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
                .pathMatchers("/api/usuarios/usuarios/**").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and()
                .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable()
                //.httpBasic().disable()
                //.formLogin().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((swe, e) -> {
//                    logger.info("[1] Authentication error: Unauthorized[401]: " + e.getMessage());
//                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));
//                })
//                .accessDeniedHandler((swe, e) -> {
//                    logger.info("[2] Authentication error: Access Denied[401]: " + e.getMessage());
//                    return Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
//                })
                .build();
    }

    // @Bean
    // CorsConfigurationSource corsConfigurationSource() {
    //     CorsConfiguration corsConfig = new CorsConfiguration();
    //     corsConfig.setAllowedOrigins(List.of("*"));
    //     corsConfig.setMaxAge(3600L);
    //     corsConfig.addAllowedMethod("*");
    //     corsConfig.addAllowedHeader("*");

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", corsConfig);
    //     return source;
    // }

}
