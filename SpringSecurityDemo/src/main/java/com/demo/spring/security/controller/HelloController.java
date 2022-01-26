package com.demo.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/greetings")
    public String greetings() {
        return "Welcome to the Spring Security Implementation!!";
    }

}
