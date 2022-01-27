package com.demo.spring.security.security.provider;

import com.demo.spring.security.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String authenticationKey = authentication.getName();
        if(authenticationKey != null && authenticationKey.equals(key)){
            CustomAuthentication auth = new CustomAuthentication(null,null,null);
            return auth;
        } else {
            throw new BadCredentialsException("Unauthorized credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
