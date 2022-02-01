package com.demo.ss.repository;

import com.demo.ss.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
    Optional<UserDetail> findUserDetailByUsername(String userName);
}
