package com.kodilla.service;

import com.kodilla.client.EmailVerificationClient;
import com.kodilla.dto.EmailVerificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationClient emailVerificationClient;

    public EmailVerificationDto verifyEmail(final String email) {
        return emailVerificationClient.verifyEmail(email);
    }
}