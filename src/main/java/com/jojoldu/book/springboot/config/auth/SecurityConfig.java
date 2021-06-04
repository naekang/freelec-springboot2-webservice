package com.jojoldu.book.springboot.config.auth;


import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        private final CustomOAuth2UserService customOAuth2UserService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .headers().frameOptions().disable()// h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                    .and()
                        .authorizeRequests() // URL별 권한 관리 설정하기 위한 시작점
                        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated()
                    .and()
                        .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 "/"로 이동
                    .and()
                        .oauth2Login()
                            .userInfoEndpoint()
                                .userService(customOAuth2UserService);
        }
}
