package com.demo.spring.security.config;

import com.demo.spring.security.security.filter.TokenAuthFilter;
import com.demo.spring.security.security.filter.UsernamePasswordCustomFilter;
import com.demo.spring.security.security.providers.OtpAuthenticationProvider;
import com.demo.spring.security.security.providers.TokenAuthProvider;
import com.demo.spring.security.security.providers.UsernameAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernameAuthenticationProvider usernameAuthenticationProvider;

    @Autowired
    private OtpAuthenticationProvider otpAuthenticationProvider;

    @Autowired
    private TokenAuthProvider tokenAuthProvider;

    @Autowired
    private TokenAuthFilter tokenAuthFilter;

    @Autowired
    private UsernamePasswordCustomFilter customFilter;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernameAuthenticationProvider)
                .authenticationProvider(otpAuthenticationProvider)
                .authenticationProvider(tokenAuthProvider);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(customFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(tokenAuthFilter, BasicAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
