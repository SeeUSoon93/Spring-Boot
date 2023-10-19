package com.soon.myhome;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //스프링의 환경설정 파일임을 의미
@EnableWebSecurity // 모든 요청이 시큐리티의 제어를 받도록 함
public class SecurityConfig {
	
	// SecurityFilterChain - 시큐리티의 세부설정
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	// 인증되지 않은 모든 요청을 허락한다는 의미(로그인 안해도 접근 가능)
        http
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
            // 시큐리티에 의해 CSRF 토큰이 자동으로 생성, 그러나 H2콘솔은 토큰 발행기능이 없어서 오류가 발생함
            // 시큐리티가 H2콘솔을 예외처리 하도록 함
            .csrf((csrf) -> csrf
                    .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
            .headers((headers) -> headers
                    .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        ;
        return http.build();
    }
    
    // PasswordEncoder - BCryptPasswordEncoder의 인터페이스
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    
}
