package com.demo.spring.security.controller;

import com.demo.spring.security.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Hello Guys!!!";
    }

    @PostMapping(value = "/createUser")
    public void addUser(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        jdbcUserDetailsManager.createUser(user);
    }
}
