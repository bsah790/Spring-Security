package com.demo.spring.security.security.providers;

import com.demo.spring.security.authentication.UsernamePasswordAuthentication;
import com.demo.spring.security.service.JpaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernameAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JpaUserDetailService jpaUserDetailService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsernameAuthenticationProvider(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = (String)authentication.getCredentials();
        UserDetails user = jpaUserDetailService.loadUserByUsername(userName);
        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            Authentication auth = new UsernamePasswordAuthentication(userName, password, user.getAuthorities());
            return auth;
        } else {
            throw new BadCredentialsException("Invalid userName/password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.equals(authentication);
    }
}
