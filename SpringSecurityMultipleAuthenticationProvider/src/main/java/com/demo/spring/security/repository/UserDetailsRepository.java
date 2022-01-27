package com.demo.spring.security.repository;

import com.demo.spring.security.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    Optional<UserDetails> findUserDetailsByUserName(String userName);
}
