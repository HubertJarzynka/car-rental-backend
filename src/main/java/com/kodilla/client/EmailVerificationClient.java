package com.kodilla.client;

import com.kodilla.config.EmailVerificationConfig;
import com.kodilla.dto.EmailVerificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class EmailVerificationClient {

    private final RestTemplate restTemplate;
    private final EmailVerificationConfig emailVerificationConfig;

    private URI getEmailVerificationUri(String email) {
        return UriComponentsBuilder.fromHttpUrl(emailVerificationConfig.getEmailVerificationApiEndpoint())
                .queryParam("apiKey", emailVerificationConfig.getEmailVerificationApiKey())
                .queryParam("emailAddress", email)
                .build().encode().toUri();
    }

    public EmailVerificationDto verifyEmail(String email) {
        URI url = getEmailVerificationUri(email);
        return restTemplate.getForObject(url, EmailVerificationDto.class);
    }
}