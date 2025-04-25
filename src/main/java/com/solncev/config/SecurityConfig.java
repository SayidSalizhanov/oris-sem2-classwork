package com.solncev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Отключение CSRF защиты (не рекомендуется для продакшна)
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/index", "/login", "/register", "/user", "/sign_up_success", "/verification").permitAll() // Разрешение доступа всем
                    .requestMatchers("/profile").authenticated() // Требуется аутентификация
                    .requestMatchers("/admin/**").hasRole("ADMIN") // Требуется роль ADMIN
                    .requestMatchers("/error/**").permitAll() // Разрешение доступа всем
                    .anyRequest().authenticated() // Любые другие запросы требуют аутентификации
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/profile")
                    .failureUrl("/login?error=true")
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
            );
        return http.build();
    }
}
