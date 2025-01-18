package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration      // spring 설정 클래스임을 나타냄
@EnableWebSecurity  // spring security를 활성화 하겠다는 의미이다.
public class SecurityConfig {

    @Bean   // BCrypt 암호화 하는 클래스 객체를 생성하여 반환하는 메서드
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // http 매개변수는 HttpSecurity객체로써 어플리케이션의 보안 설정을 구성하는데 사용된다.
    // 필터체인을 구성하는 메서드
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "login", "/join", "/joinProc").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
        );

        http
                .csrf((auth)->auth.disable());

        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );



        return http.build();
    }
}
