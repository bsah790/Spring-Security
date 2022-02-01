package com.demo.ss.repository;

import com.demo.ss.entity.ClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDetailRepository extends JpaRepository<ClientDetail, Integer> {
    Optional<ClientDetail> findClientDetailByClientId(String clientId);
}
