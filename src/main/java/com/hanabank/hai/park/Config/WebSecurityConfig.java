package com.hanabank.hai.park.Config;


import com.hanabank.hai.park.Servicepl.UserServicepl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@EnableWebSecurity
@RequiredArgsConstructor
@Configuration

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserServicepl service;

    //@Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**");
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/login", "/signup", "/user").permitAll() // 누구나 접근 허용
                .antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin() // 7
                .loginPage("/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .and()
                .logout() // 8
                .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
        ;
    }


    public void configure (AuthenticationManagerBuilder auth) throws  Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
}
