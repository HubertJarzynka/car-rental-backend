package com.kodilla.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class VinConfig {

    @Value("${vin.api.endpoint}")
    private String vinApiEndpoint;
}