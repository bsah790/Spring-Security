package com.demo.spring.security.repository;

import com.demo.spring.security.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Optional<Otp> findOtpByUserName(String userName);
    Optional<Otp> findOtpByUserNameAndOtp(String userName, String otp);
}
