package com.kodilla.controller;

import com.google.gson.Gson;
import com.kodilla.domain.Status;
import com.kodilla.dto.CarDto;
import com.kodilla.facade.CarFacade;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(CarController.class)
class CarControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarFacade carFacade;

    @Test
    void shouldFetchAllCars() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getAllCars()).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void shouldFetchCarById() throws Exception {
        //Given
        CarDto carDto = createSampleCar();
        when(carFacade.getCarById(1L)).thenReturn(carDto);
        //When & Then
        mockMvc.perform(get("/v1/cars/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.brand", is("BMW")));
    }

    @Test
    void shouldFetchAllCarsByBrand() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByBrand("BMW")).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/brand/BMW")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("brand", "BMW"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brand", is("BMW")));
    }

    @Test
    void shouldFetchAllByProductionYear() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByProductionYear(2020)).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/year/2020")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("productionYear", String.valueOf(2020)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].productionYear", is(2020)));
    }

    @Test
    void shouldFetchAllCarsByMileage() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByMileage(12345)).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/mileage/12345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("mileage", String.valueOf(12345)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].mileage", is(12345)));
    }

    @Test
    void shouldFetchAllCarsByFuel() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByFuelType("Petrol")).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/fuel/Petrol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("fuel", "Petrol"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].fuel", is("Petrol")));
    }

    @Test
    void shouldFetchAllByDailyCost() throws Exception {
        //Given
        List<CarDto> carDtoList = createSampleCarList();
        when(carFacade.getCarsByDailyCost(new BigDecimal("111.11"))).thenReturn(carDtoList);
        //When & Then
        mockMvc.perform(get("/v1/cars/cost/111.11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("cost", String.valueOf(111.11)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].dailyCost", is(111.11)));
    }

    @Test
    void shouldCreateCar() throws Exception {
        //Given
        CarDto carDto = createSampleCar();
        when(carFacade.saveCar(ArgumentMatchers.any(CarDto.class))).thenReturn(carDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //When & Then
        mockMvc.perform(post("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.vin", is("1234")))
                .andExpect(jsonPath("$.brand", is("BMW")));
    }

    @Test
    void shouldUpdateCar() throws Exception {
        //Given
        CarDto carDto = createSampleCar();
        when(carFacade.saveCar(ArgumentMatchers.any(CarDto.class))).thenReturn(carDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //When & Then
        mockMvc.perform(put("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.brand", is("BMW")))
                .andExpect(jsonPath("$.model", is("3")));
    }


    private CarDto createSampleCar() {
        return CarDto.builder()
                .id(1L)
                .brand("BMW")
                .model("3")
                .fuel("Petrol")
                .productionYear(2020)
                .vin("1234")
                .mileage(12345)
                .dailyCost(new BigDecimal("111.11"))
                .status(Status.AVAILABLE)
                .build();
    }

    private List<CarDto> createSampleCarList() {
        CarDto carDto = createSampleCar();
        return Collections.singletonList(carDto);
    }
}
