package com.heesue.mindbridge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 리소스들이 보안필터를 거치지 않게끔
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/font/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable()                               // cors 방지
                .csrf().disable()                           // csrf 방지
                .headers().frameOptions().disable();        // x frame 방어 해제

        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN") // 관리자 권한을 요구하는 경로
                .antMatchers("/member/**").hasRole("CLIENT") // 회원 권한을 요구하는 경로
                .antMatchers("/login", "/signin", "/").permitAll() // 로그인 및 회원가입은 모든 사용자에게 허용
                .anyRequest().authenticated(); // 그 외의 모든 요청은 인증된 사용자만 접근 가능하도록 설정


        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/member/mypage")
                .failureUrl("/")
                .usernameParameter("id")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");


        // status code 핸들링
        http.exceptionHandling().accessDeniedPage("/denied");

        return http.build();
    }
}
