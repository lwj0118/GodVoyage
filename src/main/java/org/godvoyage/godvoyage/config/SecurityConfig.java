package org.godvoyage.godvoyage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //loginPage("") : 사용자 로그인 페이지
        //defaultSuccessUrl("/")  : 로그인 시 이동할 페이지
        //loginProcessingUrl("/loginProc") : 사용자로그인 페이지, form action속성값
        http.formLogin(login->login.loginPage("/member/login")
                //로그인 성공시 보낼 url
                .defaultSuccessUrl("/", true)
                .loginProcessingUrl("/loginProc")
                //실패시 보낼 url. 결과는 login페이지로 이동시키기임
                .failureUrl("/member/login/error")
        );
        http.logout((auth)->auth.logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );
        //페이지 경로 권한지정. 역할별 공개할 것들. permitAll==> 비로그인시에도 허용 가능
        http.authorizeHttpRequests((auth)->auth
                .requestMatchers("/","/member/**","/item/**","/images/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/js/**","/css/**","/img/**","/image/**","/fonts/**","/vendor/**").permitAll()
                .anyRequest().authenticated()
        );
        //위변조방지 csrf토큰 체크 사용 안함 설정
        http.csrf(cs->cs.disable());
        return http.build();
    }
}

