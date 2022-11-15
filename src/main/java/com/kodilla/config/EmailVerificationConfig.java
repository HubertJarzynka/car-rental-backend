package com.kodilla.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class EmailVerificationConfig {
    @Value("${emailverification.api.endpoint}")
    private String emailVerificationApiEndpoint;

    @Value("${emailverification.api.key}")
    private String emailVerificationApiKey;
}