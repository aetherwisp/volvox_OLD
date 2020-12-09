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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import com.github.aetherwisp.volvox.application.security.auth.VolvoxAuthenticationFailureHandler;
import com.github.aetherwisp.volvox.application.security.auth.VolvoxAuthenticationProvider;
import com.github.aetherwisp.volvox.application.security.auth.VolvoxAuthenticationSuccessHandler;
import com.github.aetherwisp.volvox.domain.user.UserRepository;

/**
 * By adding {@code @EnableWebSecurity} we get Spring Security and MVC integration support.
 */
@Configuration
@EnableWebSecurity
public class VolvoxWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    //======================================================================
    // Fields
    private final UserRepository userRepository;

    @Autowired
    public VolvoxWebSecurityConfiguration(final UserRepository _userRepository) {
        this.userRepository = Objects.requireNonNull(_userRepository);
    }

    //======================================================================
    // Methods
    @Override
    public void configure(WebSecurity _web) throws Exception {
        _web.ignoring()
            .antMatchers("/favicon.png", "/app/**", "/lib/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity _http) throws Exception {

        // Addresses that allow access even when you are not logged in.
        _http.authorizeRequests()
            .antMatchers("/", "/index", "/login", "/logout", "/error")
            .permitAll()
            .anyRequest()
            .authenticated();

        // CSRF Configuration
        _http.csrf()
            .csrfTokenRepository(new CookieCsrfTokenRepository());


        _http.formLogin()
            .loginPage("/index")
            .loginProcessingUrl("/login")
            .successHandler(new VolvoxAuthenticationSuccessHandler("/menu"))
            .failureHandler(new VolvoxAuthenticationFailureHandler("/index"))
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
