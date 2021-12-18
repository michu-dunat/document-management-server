package com.example.documentmanagementserver.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/case/delete/*").hasAnyRole("ADMIN")
                .antMatchers("/case/status/*").hasAnyRole("ADMIN")
                .antMatchers("/document/delete/*").hasAnyRole("ADMIN")
                .antMatchers("/user/possible-document-senders").hasAnyRole("ADMIN", "USER")
                .antMatchers("/user/*").hasAnyRole("ADMIN")
                .antMatchers("/role").permitAll()
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .and().cors().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
