package com.kodilla.facades;

import com.kodilla.domain.Car;
import com.kodilla.domain.Status;
import com.kodilla.dto.CarDto;
import com.kodilla.exception.CarNotFoundException;
import com.kodilla.facade.CarFacade;
import com.kodilla.mapper.CarMapper;
import com.kodilla.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class CarFacadeTestSuite {

    @InjectMocks
    private CarFacade carFacade;

    @Mock
    private CarService carService;

    @Mock
    private CarMapper carMapper;

    @Test
    void shouldGetAllCars() {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getAllCars()).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getAllCars();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getStatus(), carDtoList.get(0).getStatus());
        });
    }

    @Test
    void shouldGetCarById() throws CarNotFoundException {
        //Given
        Car car = initCar();
        CarDto carDto = initCarDto();

        when(carService.getCarById(1L)).thenReturn(car);
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);

        //When
        CarDto result = carFacade.getCarById(1L);

        //Then
        assertEquals(carDto.getId(), result.getId());
        assertEquals(carDto.getBrand(), result.getBrand());
    }

    @Test
    void shouldGetCarsByBrand() throws CarNotFoundException {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByBrand("BMW")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByBrand("BMW");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getBrand(), carDtoList.get(0).getBrand());
        });
    }

    @Test
    void shouldGetCarsByFuelType() throws CarNotFoundException {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByFuelType("Petrol")).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByFuelType("Petrol");

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getFuel(), carDtoList.get(0).getFuel());
        });
    }

    @Test
    void shouldGetCarsByMileage() throws CarNotFoundException {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByMileage(260000)).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByMileage(260000);

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getMileage(), carDtoList.get(0).getMileage());
        });
    }

    @Test
    void shouldGetCarByDailyCost() throws CarNotFoundException {
        //Given
        List<Car> carList = initCarList();
        List<CarDto> carDtoList = initCarDtoList();

        when(carService.getCarsByDailyCost(new BigDecimal(32))).thenReturn(carList);
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);

        //When
        List<CarDto> resultList = carFacade.getCarsByDailyCost(new BigDecimal(32));

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(c -> {
            assertEquals(c.getId(), carDtoList.get(0).getId());
            assertEquals(c.getDailyCost(), carDtoList.get(0).getDailyCost());
        });
    }

    @Test
    void shouldSaveCar() {
        //Given
        Car car = initCar();
        CarDto carDto = initCarDto();

        when(carMapper.mapToCar(any())).thenReturn(car);
        when(carMapper.mapToCarDto(any())).thenReturn(carDto);

        //When
        CarDto savedCar = carFacade.saveCar(carDto);

        //Then
        assertEquals(carDto.getId(), savedCar.getId());
        assertEquals(carDto.getBrand(), savedCar.getBrand());
        assertEquals(carDto.getDailyCost(), savedCar.getDailyCost());
    }

    @Test
    void shouldDeleteCar() {
        //Given
        //When
        carFacade.deleteCar(2L);

        //Then
        verify(carService, times(1)).deleteCar(2L);
    }

    private Car initCar() {
        return Car.builder()
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

    private CarDto initCarDto() {
        return CarDto.builder()
                .id(2L)
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

    private List<Car> initCarList() {
        Car car = initCar();
        return Collections.singletonList(car);
    }

    private List<CarDto> initCarDtoList() {
        CarDto carDto = initCarDto();
        return Collections.singletonList(carDto);
    }
}

