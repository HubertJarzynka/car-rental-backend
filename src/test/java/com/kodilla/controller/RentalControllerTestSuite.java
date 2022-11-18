package com.kodilla.controller;

import com.kodilla.dto.FullRentalDto;
import com.kodilla.facade.RentalFacade;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(RentalController.class)
public class RentalControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalFacade rentalFacade;

    @Test
    void shouldFetchAllRentals() throws Exception {
        //Given
        FullRentalDto rentalWithCarDto = createSampleRental();
        List<FullRentalDto> rentalWithCarDtoList = Collections.singletonList(rentalWithCarDto);
        doReturn(rentalWithCarDtoList).when(rentalFacade).getAllRentals();
        //When & Then
        mockMvc.perform(get("/v1/rentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].carBrand", is("BMW")));
    }

    @Test
    void shouldFetchAllRentalsById() throws Exception {
        //Given
        FullRentalDto rentalWithCarDto = createSampleRental();
        when(rentalFacade.getRentalById(1L)).thenReturn(rentalWithCarDto);
        //When & Then
        mockMvc.perform(get("/v1/rentals/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)));
    }


    @Test
    void shouldCloseRental() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/rentals/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("id", "1"))
                .andExpect(status().is(200));
    }

    private FullRentalDto createSampleRental() {
        return FullRentalDto.builder()
                .id(1L)
                .rentedFrom(LocalDate.of(2022, 1, 11))
                .rentedUntil(LocalDate.of(2022,  2, 1))
                .carId(1L)
                .carBrand("BMW")
                .carModel("3")
                .userName("Janusz")
                .userSurname("Kowalski")
                .userMail("janusz.kowalski@gmail.com")
                .userPhoneNumber(123456789)
                .build();
    }
}
