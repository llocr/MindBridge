package com.heesue.mindbridge.config;

import com.heesue.mindbridge.jwt.JwtAuthenticationFilter;
import com.heesue.mindbridge.jwt.JwtTokenProvider;
import com.heesue.mindbridge.service.ClientDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final ClientDetailsService clientDetailsService;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, ClientDetailsService clientDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.clientDetailsService = clientDetailsService;
    }

    //비밀번호 암호화에 사용할 객체 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 사용자 인증을 위한 Service bean 등록, 사용할 비밀번호 인코딩 방식 설정
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(clientDetailsService).passwordEncoder(passwordEncoder());
        return builder.build();
    }

    //http 요청에 대한 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable();


        // 권한에 따라 허용하는 url 설정
        http.httpBasic().disable()
                .authorizeRequests()
                        .antMatchers("/test").authenticated()
                        .antMatchers("/admin").hasRole("ADMIN")
                        .antMatchers("/**").permitAll();
//                        .and()
//                                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//                                        UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


//        http.authorizeRequests()
//                .antMatchers("/index", "/client/join", "client/login" , "/css/**", "/js/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/client/myPage").hasRole("CLIENT")
//                .anyRequest().permitAll();
//
//        // login 설정
//        http.formLogin()
//                .loginPage("/client/login")    // GET 요청 (login form을 보여줌)
//                .defaultSuccessUrl("/")	// login에 성공하면 /로 redirect
//                .loginProcessingUrl("/client/login")    // POST 요청 (login 창에 입력한 데이터를 처리)
//                .usernameParameter("id")	// login에 필요한 id 값을 설정 (default는 username)
//                .passwordParameter("password")	// login에 필요한 password 값을 password(default)로 설정
//                .failureForwardUrl("/client/login"); //로그인 실패 시 랜딩 페이지
//
//        // logout 설정
//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/client/logout"))
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/");	// logout에 성공하면 /로 redirect

        return http.build();
    }
}
