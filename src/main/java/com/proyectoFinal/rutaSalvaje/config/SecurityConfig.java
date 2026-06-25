package com.proyectoFinal.rutaSalvaje.config;

import com.proyectoFinal.rutaSalvaje.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtFilter jwtFilter, AuthenticationProvider authenticationProvider) {
        this.jwtFilter = jwtFilter;
        this.authenticationProvider = authenticationProvider;
    }

    // IGNORAR TOTALMENTE SWAGGER A NIVEL WEB (Solución radical para producción)
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html"
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/contactos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/planes/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/resenas/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/resenas/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reservas/disponibilidad").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reservas/usuario/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/reservas").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/reservas/*/cancelar-usuario").authenticated()

                        .requestMatchers(HttpMethod.PUT, "/auth/promover/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/resenas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/planes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/planes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/planes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/reservas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/reservas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/reservas/**").hasRole("ADMIN")
                        .requestMatchers("/usuarios/**", "/contactos/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of(
                "http://127.0.0.1:*",
                "http://localhost:*",
                "https://juranyr.github.io",
                "https://*.vercel.app"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}