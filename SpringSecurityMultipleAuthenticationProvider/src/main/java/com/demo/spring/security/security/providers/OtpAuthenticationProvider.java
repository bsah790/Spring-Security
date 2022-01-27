package com.demo.spring.security.security.providers;

import com.demo.spring.security.authentication.OtpAuthentication;
import com.demo.spring.security.entity.Otp;
import com.demo.spring.security.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String otpStr = (String)authentication.getCredentials();
        Optional<Otp> otpObj = otpRepository.findOtpByUserNameAndOtp(userName, otpStr);
        Otp otp = otpObj.orElseThrow(() -> new BadCredentialsException("Invalid Otp"));
        if(otp != null && otpStr.equals(otp.getOtp())) {
            Authentication auth = new OtpAuthentication(userName, otp, null);
            return auth;
        } else {
            throw new BadCredentialsException("Invalid Otp!!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
