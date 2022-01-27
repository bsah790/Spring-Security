package com.demo.spring.security.service;

import com.demo.spring.security.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserDetailService implements UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<com.demo.spring.security.entity.UserDetails> userDetails =
                userDetailsRepository.findUserDetailsByUserName(userName);
        com.demo.spring.security.entity.UserDetails user = userDetails.orElseThrow(
                () -> new BadCredentialsException(""));
        return new JpaUserDetials(user);
    }
}
