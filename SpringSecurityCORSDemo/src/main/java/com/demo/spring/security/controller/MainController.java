package com.demo.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String hello(){
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
   // @CrossOrigin("*")
    public String test(){
        System.out.println("called!!!");
        return "Hello World!!";
    }
}
