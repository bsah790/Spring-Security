package com.demo.spring.security.service;

import com.demo.spring.security.entity.UserDetail;
import com.demo.spring.security.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JPAUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetail> user = userDetailRepository.findUserDetailByUserName(username);
        UserDetail u = user.orElseThrow(() -> new UsernameNotFoundException("Error while getting user"));
        return new JPAUserDetails(u);
    }
}
