package com.bestrongkids.chikingauthorizationserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(
                                        "/register",
                                        "/assets/**",
                                        "/webjars/**",
                                        "/login"
                                        ).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable
                )
//                .oauth2Login(oauth2Login ->
//                        oauth2Login
//                                .loginPage("/login")
//                                .successHandler(authenticationSuccessHandler())
//                )
                ;

        return http.build();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
}
