package com.demo.spring.security.security.manager;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenManager {

    private Set<String> tokenSet = new HashSet<>();

    public void addToken(String token){
        tokenSet.add(token);
    }

    public boolean isTokenPresent(String token){
        return tokenSet.contains(token);
    }
}
