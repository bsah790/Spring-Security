package com.demo.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().anyRequest().permitAll();
        http.cors(c -> {
            CorsConfigurationSource cc = (r) -> {
                CorsConfiguration cors =  new CorsConfiguration();
                String allowedOrigin = env.getProperty("allowed-cors-origin");
                String allowedMethod =  env.getProperty("allowed-method");
               List<String> allowedOriginList =  Arrays.asList(allowedOrigin.split(",", -1));
                List<String> allowedMethodList =  Arrays.asList(allowedMethod.split(",", -1));
                cors.setAllowedOrigins(allowedOriginList);
                cors.setAllowedMethods(allowedMethodList);
                return cors;
            };
            c.configurationSource(cc);
        });

    }
}
