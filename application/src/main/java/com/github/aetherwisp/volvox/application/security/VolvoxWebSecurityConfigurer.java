package com.github.aetherwisp.volvox.application.security;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.github.aetherwisp.volvox.application.security.auth.VolvoxAuthenticationProvider;
import com.github.aetherwisp.volvox.domain.user.UserRepository;

/**
 * By adding {@code @EnableWebSecurity} we get Spring Security and MVC integration support.
 */
@Configuration
@EnableWebSecurity
public class VolvoxWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    //======================================================================
    // Fields
    private final UserRepository userRepository;

    @Autowired
    public VolvoxWebSecurityConfigurer(final UserRepository _userRepository) {
        this.userRepository = Objects.requireNonNull(_userRepository);
    }

    //======================================================================
    // Methods
    @Override
    public void configure(WebSecurity _web) throws Exception {
        _web.ignoring()
                .antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity _http) throws Exception {

        // Addresses that allow access even when you are not logged in.
        _http.authorizeRequests()
                .antMatchers("/", "/error")
                .permitAll()
                .anyRequest()
                .authenticated();


        _http.formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationProvider());
    }

    //======================================================================
    // Components
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new VolvoxAuthenticationProvider(this.passwordEncoder(), this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
