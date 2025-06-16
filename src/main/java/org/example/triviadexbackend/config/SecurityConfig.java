package org.example.triviadexbackend.config;

import org.example.triviadexbackend.config.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // 🔓 Endpoints públicos
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/characters", "/api/characters/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/likes/count/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/scores/ranking").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/comments/character/**").permitAll()

                        // 🔐 Endpoints protegidos (requieren login)
                        .requestMatchers(HttpMethod.POST, "/api/comments").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/scores/trivia").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/scores/daily").authenticated()
                        .requestMatchers("/api/likes/**").authenticated()
                        .requestMatchers("/api/users/profile").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/trivia/**").authenticated()

                        // 🛡️ Endpoints de administración
                        .requestMatchers(HttpMethod.POST, "/api/characters").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/characters/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/characters/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/trivia").hasRole("ADMIN")
                        .requestMatchers("/api/users").hasRole("ADMIN")
                        .requestMatchers("/api/users/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/users/{id}/role").hasRole("ADMIN")
                        .requestMatchers("/api/users/email/{email}").hasRole("ADMIN")

                        // 🔒 Todo lo demás protegido
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://3.230.226.84", // producción
                "http://127.0.0.1:5500" // desarrollo local
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
