package com.demo.ss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(converter());
    }

    @Bean
    public JwtAccessTokenConverter converter() {
        JwtAccessTokenConverter jwt = new JwtAccessTokenConverter();
        jwt.setSigningKey("secret");
        return jwt;
    }

}
