package com.demo.spring.security.config;

import com.demo.spring.security.security.token.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfiguration  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
       // http.csrf().disable();
        http.csrf( c -> {
                    c.ignoringAntMatchers("/ignoreCsrf/**");
                   // c.csrfTokenRepository(new CustomCsrfTokenRepository());
                }
        );
    }
}
