package com.fish.universityeducationalsystem.config;

import com.fish.universityeducationalsystem.exception.ServiceExceptionEnum;
import com.fish.universityeducationalsystem.utils.ResultUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.exceptionHandling(configurer -> {
            configurer.accessDeniedHandler((request, response, accessDeniedException) -> {
                ResultUtil.failure(response, ServiceExceptionEnum.INSUFFICIENT_PERMISSIONS);
            });
            configurer.authenticationEntryPoint((request, response, authException) -> {
                ResultUtil.failure(response, ServiceExceptionEnum.AUTHENTICATION_FAILURE);
            });
        });
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
