package com.github.aetherwisp.volvox.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * By adding {@code @EnableWebSecurity} we get Spring Security and MVC integration support.
 */
@Configuration
@EnableWebSecurity
public class VolvoxWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity _web) throws Exception {
        _web.ignoring()
                .antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity _http) throws Exception {
        // Addresses that allow access even when you are not logged in.
        _http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/login", "/error")
                .permitAll()
                .anyRequest()
                .authenticated();


    }
}
