package com.kodilla.service;

import com.kodilla.client.VinClient;
import com.kodilla.dto.VinApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class VinService {

    private final VinClient vinClient;

    public VinApiDto decodeVin(String vin) {
        return vinClient.decodeVin(vin);
    }
}