package com.BHstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF để React gọi được API POST
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests((requests) -> requests
                        // 1. CHO PHÉP REACT GỌI MỌI API (Quan trọng nhất)
                        .requestMatchers("/api/**").permitAll()

                        // 2. Các file tĩnh (ảnh, css)
                        .requestMatchers("/css/**", "/images/**", "/js/**").permitAll()

                        // 3. Các trang cũ (nếu còn dùng)
                        .requestMatchers("/", "/login", "/register").permitAll()

                        // 4. Còn lại phải đăng nhập
                        .anyRequest().authenticated());

        // Tắt Form Login mặc định vì ta đang dùng React
        // .formLogin(...) -> Xóa hoặc comment lại đoạn này cũng được

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}