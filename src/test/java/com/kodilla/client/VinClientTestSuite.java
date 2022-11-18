package com.kodilla.client;

import com.kodilla.config.VinConfig;
import com.kodilla.dto.VinApiDto;
import com.kodilla.dto.VinBodyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VinClientTestSuite {

    @InjectMocks
    private VinClient vinClient;

    @Mock
    private VinConfig vinConfig;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void shouldDecodeVin() throws URISyntaxException {

        when(vinConfig.getVinApiEndpoint()).thenReturn("https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues");

        //Given
        VinBodyDto vinBodyDto = VinBodyDto.builder()
                .manufacturer("BMW")
                .model("3")
                .productYear("2004")
                .vehicleType("PASSENGER CAR")
                .build();

        List<VinBodyDto> vinBodyDtoList = Collections.singletonList(vinBodyDto);
        VinApiDto vinApiDto = new VinApiDto(vinBodyDtoList);

        URI uri = new URI("https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/WBAEU71050KV91574?format=json");
        when(restTemplate.getForObject(uri, VinApiDto.class)).thenReturn(vinApiDto);

        //When
        VinApiDto result = vinClient.decodeVin("WBAEU71050KV91574");

        //Then
        assertEquals("BMW", result.getVinBodyDtoList().get(0).getManufacturer());
        assertEquals("3", result.getVinBodyDtoList().get(0).getModel());

    }

}

