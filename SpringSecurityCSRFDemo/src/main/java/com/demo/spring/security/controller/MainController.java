package com.demo.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String greetings(){
        return "main.html";
    }

    @PostMapping("/changes")
    public String change(){
        System.out.println("change() :: called");
        return "main.html";
    }

    @PostMapping("/ignoreCsrf/another")
    public String call(){
        System.out.println("call() :: called");
        return "main.html";
    }
}
