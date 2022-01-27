package com.demo.spring.security.security.filter;

import com.demo.spring.security.authentication.OtpAuthentication;
import com.demo.spring.security.authentication.UsernamePasswordAuthentication;
import com.demo.spring.security.entity.Otp;
import com.demo.spring.security.repository.OtpRepository;
import com.demo.spring.security.security.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Component
public class UsernamePasswordCustomFilter extends OncePerRequestFilter {
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    public UsernamePasswordCustomFilter(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userName = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");
        Authentication authentication = null;

        if(password != null) {
            authentication = new UsernamePasswordAuthentication(userName, password);
            try {
                authentication = authenticationManager.authenticate(authentication);
                if(authentication.isAuthenticated()) {
                    Otp otpEntity = new Otp();
                    otpEntity.setUserName(userName);
                    otpEntity.setOtp(String.valueOf((new Random().nextInt(9999) + 1000)));
                    otpRepository.save(otpEntity);
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }

            } catch (AuthenticationException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
              //  throw new BadCredentialsException("Invalid Username/password");
            }
        } else if(otp != null) {
            authentication = new OtpAuthentication(userName, otp);
            try {
                authentication = authenticationManager.authenticate(authentication);
                if(authentication.isAuthenticated()) {
                    String token = UUID.randomUUID().toString();
                    tokenManager.addToken(token);
                    response.setHeader("Token", token);
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            } catch (AuthenticationException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
               // throw new BadCredentialsException("Invalid Username/Otp");
            }
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
