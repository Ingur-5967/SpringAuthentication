package ru.solomka.authentication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.solomka.authentication.configuration.filter.PerAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecureConfiguration {

    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity http,
                                            AuthenticationManager manager,
                                            PerAuthenticationFilter filter) throws Exception {

        http
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/v1/authentication/**").permitAll()
                            .anyRequest()
                            .authenticated();
                }).exceptionHandling(AbstractHttpConfigurer::disable)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(manager);

        return http.build();
    }
}