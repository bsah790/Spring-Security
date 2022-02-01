package com.demo.ss.service;

import com.demo.ss.entity.ClientDetail;
import com.demo.ss.repository.ClientDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.util.Optional;

public class JpaClientDetailService implements ClientDetailsService {

    @Autowired
    private ClientDetailRepository clientDetailRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<ClientDetail> clientDetailOptional = clientDetailRepository.findClientDetailByClientId(clientId);
        ClientDetail clientDetail = clientDetailOptional.orElseThrow(() -> new ClientRegistrationException("Client id not found"));
        return new JpaClientDetails(clientDetail);
    }
}
