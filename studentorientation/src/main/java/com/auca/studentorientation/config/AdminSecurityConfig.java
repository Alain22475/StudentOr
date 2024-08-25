package com.auca.studentorientation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AdminSecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/admin/**") // Apply this security config only to /admin/** paths
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin/login", "/css/**").permitAll() // Public admin pages
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Admin access
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/admin/login") // Admin login page
                        .defaultSuccessUrl("/admin/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF if needed (not recommended for production)

        return http.build();
    }
}
