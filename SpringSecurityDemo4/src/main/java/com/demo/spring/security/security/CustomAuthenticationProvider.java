package com.demo.spring.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // if the request is authenticated then return a fully authenticated Authentication instance
        // if user is not authenticated then throw AuthenticationException
        // if authentication is not supported by this Ap then return null

        String userName = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        UserDetails u = userDetailsService.loadUserByUsername(userName);
        if(u != null){
            if(passwordEncoder.matches(password, u.getPassword())) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userName, password, u.getAuthorities());
                return authenticationToken;
            }
        } else {
            throw new BadCredentialsException("Bad Credential!");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authType) {
        return UsernamePasswordAuthenticationToken.class.equals(authType);
    }
}
