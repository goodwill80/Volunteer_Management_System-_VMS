package com.ntu.sctp.group1.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfigurationFilterChain {

    @Autowired
    FirebaseFilter firebaseFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable() // New Line: the h2 console runs on a "frame". By default, Spring Security prevents rendering within an iframe. This line disables its prevention.
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/h2/**"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/actuator/**"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v3/**"))
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(firebaseFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors();

        http.oauth2ResourceServer().jwt();
        return http.build();
    }

}