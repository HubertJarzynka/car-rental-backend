package com.kodilla.mapper;

import com.kodilla.domain.Car;
import com.kodilla.domain.Status;
import com.kodilla.dto.CarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

public class CarMapperTestSuite {

    @InjectMocks
    private CarMapper carMapper;

    @Test
    void mapToCarTest() {
        //Given
        CarDto carDto = initCarDto();
        //When
        Car car = carMapper.mapToCar(carDto);
        //Then
        assertEquals("1234", car.getVin());
        assertEquals("Petrol", car.getFuel());
        assertEquals(Status.AVAILABLE, car.getStatus());
    }

    @Test
    void mapToCarDtoTest() {
        //Given
        Car car = initCar();
        //When
        CarDto carDto = carMapper.mapToCarDto(car);
        //Then
        assertEquals("1234", carDto.getVin());
        assertEquals("Petrol", carDto.getFuel());
        assertEquals(Status.AVAILABLE, carDto.getStatus());
    }

    @Test
    void mapToCarDtoList() {
        //Given
        Car car = initCar();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        //When
        List<CarDto> carDtoList = carMapper.mapToCarDtoList(carList);
        //Then
        assertEquals(1, carDtoList.size());
        assertEquals(2020, carDtoList.get(0).getProductionYear());
    }


    private Car initCar() {
        return Car.builder()
                .id(1L)
                .vin("1234")
                .brand("BMW")
                .model("3")
                .productionYear(2020)
                .mileage(12345)
                .fuel("Petrol")
                .dailyCost(new BigDecimal("200"))
                .status(Status.AVAILABLE)
                .build();

    }

    private CarDto initCarDto() {
        return CarDto.builder()
                .id(1L)
                .vin("1234")
                .brand("BMW")
                .model("3")
                .productionYear(2020)
                .mileage(12345)
                .fuel("Petrol")
                .dailyCost(new BigDecimal("200"))
                .status(Status.AVAILABLE)
                .build();
    }

}
