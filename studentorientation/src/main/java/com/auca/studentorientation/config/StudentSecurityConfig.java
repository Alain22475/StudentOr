package com.auca.studentorientation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class StudentSecurityConfig {

    @Bean
    public SecurityFilterChain studentSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/student/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/student/login", "/student/signup", "/css/**").permitAll()
                        .requestMatchers("/student/**").hasRole("STUDENT")
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/student/login")
                        .loginProcessingUrl("/student/login")
                        .defaultSuccessUrl("/student/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/student/logout")
                        .logoutSuccessUrl("/student/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}
