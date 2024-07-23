package com.example.BlogExercise.security;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.client.RestClient;

public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "apikey";

    public static Authentication getAuthentication(HttpServletRequest request) {

        final RestClient restClient=RestClient.builder().baseUrl("http://localhost:8081").build();
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        boolean isValid= Boolean.TRUE.equals(restClient.get().uri("/token/validate?token={token}", apiKey).retrieve().body(Boolean.class));

        if (apiKey == null || !isValid) {
            throw new BadCredentialsException("Invalid apiKey");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}