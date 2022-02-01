package com.demo.ss.service;

import com.demo.ss.entity.ClientDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

public class JpaClientDetails implements ClientDetails {


    private final ClientDetail client;

    public JpaClientDetails(ClientDetail client) {
        this.client = client;
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }


    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return client.getSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        Set<String> scopeSet = new HashSet<>();
        scopeSet.add(client.getScope());
        return scopeSet;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> grantTypes = new HashSet<>();
        grantTypes.add(client.getGrantType());
        grantTypes.add("refresh_token");
        return grantTypes;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> client.getScope());
        return authorities;
    }


    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        Set<String> redirectedUri = new HashSet<>();
        redirectedUri.add("http://localhost:9090");
        return redirectedUri;
    }


    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
