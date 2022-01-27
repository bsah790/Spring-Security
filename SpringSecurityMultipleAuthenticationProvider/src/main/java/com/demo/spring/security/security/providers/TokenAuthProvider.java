package com.demo.spring.security.security.providers;

import com.demo.spring.security.authentication.TokenAuthentication;
import com.demo.spring.security.security.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        if(tokenManager.isTokenPresent(token)){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(() -> "read");
            Authentication auth = new TokenAuthentication(token, null, authorities);
            return auth;
        } else {
            throw new BadCredentialsException("Bad Token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
