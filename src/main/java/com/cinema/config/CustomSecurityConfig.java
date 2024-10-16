package com.cinema.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;


@Configuration
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });

        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(config -> config.disable());      // csrf 설정

//         URL 접근 제어 설정
//        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers(
//                        new AntPathRequestMatcher("/"),
//                        new AntPathRequestMatcher("/api/member/joinpage"),
//                        new AntPathRequestMatcher("/api/member/check-id"),
//                        new AntPathRequestMatcher("/api/member/login"))
//                .permitAll()  // 회원가입과 로그인 페이지는 인증 없이 접근 가능
//                .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
//        );

        // formLogin 설정을 auth 밖에서 별도로 설정
        http.formLogin(config -> {
            config.loginPage("/api/member/login")      // 로그인 페이지
                    .successHandler(new APILoginSuccessHandler())
                    .failureHandler(new APILoginFailHandler())
                    .usernameParameter("id")             // 사용자 이름 필드 이름 설정
                    .passwordParameter("password");
//                    .successHandler(new APILoginSuccessHandler())
//                    .failureHandler(new APILoginFailHandler())
//                    .defaultSuccessUrl("/")         // 로그인 성공 시 이동할 페이지
//                    .permitAll();
        });

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {      // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
