package com.demo.ss.service;

import com.demo.ss.entity.UserDetail;
import com.demo.ss.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetail> userDetailOptional = userDetailRepository.findUserDetailByUsername(username);
        UserDetail userDetail = userDetailOptional.orElseThrow(() -> new UsernameNotFoundException("Unable to find user"));
        return new JpaUserDetails(userDetail);
    }
}
