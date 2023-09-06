package com.heesue.mindbridge.config;

import com.heesue.mindbridge.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationService authenticationService;

    @Autowired
    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    //비밀번호 암호화에 사용할 객체 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //http 요청에 대한 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 권한에 따라 허용하는 url 설정
        // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
        http.authorizeRequests()
                .antMatchers("/index", "/client/**", "/css/**", "/js/**").permitAll()
                .anyRequest().permitAll();

        // login 설정
        http.formLogin()
                .loginPage("/client/login")    // GET 요청 (login form을 보여줌)
                .defaultSuccessUrl("/")	// login에 성공하면 /로 redirect
                .loginProcessingUrl("/client/new")    // POST 요청 (login 창에 입력한 데이터를 처리)
                .usernameParameter("id")	// login에 필요한 id 값을 email로 설정 (default는 username)
                .passwordParameter("password")	// login에 필요한 password 값을 password(default)로 설정
                .failureForwardUrl("/error/login"); //로그인 실패 시 랜딩 페이지

        // logout 설정
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/client/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/");	// logout에 성공하면 /로 redirect


        return http.build();
    }

    // 사용자 인증을 위한 Service bean 등록, 사용할 비밀번호 인코딩 방식 설정
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authenticationService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
