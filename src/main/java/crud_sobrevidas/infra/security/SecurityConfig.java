package crud_sobrevidas.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/token/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pacientes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pacientes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }
}
