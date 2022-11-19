package com.kodilla.service;

import com.kodilla.domain.Car;
import com.kodilla.domain.Rental;
import com.kodilla.domain.Status;
import com.kodilla.domain.User;
import com.kodilla.dto.RentalDto;
import com.kodilla.exception.CarNotFoundException;
import com.kodilla.exception.RentalNotFoundException;
import com.kodilla.exception.UserNotFoundException;
import com.kodilla.repository.CarRepository;
import com.kodilla.repository.RentalRepository;
import com.kodilla.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RentalServiceTestSuite {

    @InjectMocks
    private RentalService rentalService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @Test
    void shouldGetAllRentals() {
        //Given
        Rental rental = initRental();
        List<Rental> rentalList = Collections.singletonList(rental);

        when(rentalRepository.findAll()).thenReturn(rentalList);

        //When
        List<Rental> resultList = rentalService.getAllRentals();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    void shouldGetRentalById() throws RentalNotFoundException {
        //Given
        Rental rental = initRental();

        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        //When
        Rental result = rentalService.getRentalById(1L);

        //Then
        assertEquals(result.getId(), result.getId());
    }

    @Test
    void shouldCreateRental() throws UserNotFoundException, CarNotFoundException {
        //Given
        User user = initUser();
        Car car = initCar();
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(carRepository.findById(any())).thenReturn(Optional.of(car));
        when(rentalRepository.save(any())).thenReturn(rental);

        //When
        Rental createdRental = rentalService.createRental(rentalDto);

        //Then
        assertEquals(createdRental.getCar().getBrand(), car.getBrand());
        assertEquals(createdRental.getUser().getName(), user.getName());
        assertEquals(createdRental.getRentedFrom(), LocalDate.of(2022, 1, 1));
        assertEquals(createdRental.getRentedUntil(), LocalDate.of(2022, 1, 1));
    }

    @Test
    void shouldUpdateRental() throws UserNotFoundException, CarNotFoundException, RentalNotFoundException {
        //Given
        User user = initUser();
        Car car = initCar();
        Rental rental = initRental();
        RentalDto rentalDto = initRentalDto();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));

        //When
        Rental modifiedRental = rentalService.updateRental(rentalDto);

        //Then
        assertEquals(rentalDto.getUserId(), modifiedRental.getUser().getId());
        assertEquals(rentalDto.getCarId(), modifiedRental.getCar().getId());
        assertEquals(rentalDto.getRentedFrom(), modifiedRental.getRentedFrom());
        assertEquals(rentalDto.getRentedUntil(), modifiedRental.getRentedUntil());
    }


    private User initUser() {
        return User.builder()
                .id(1L)
                .name("Jan")
                .surname("Kowalski")
                .password("password")
                .mail("mail")
                .phoneNumber(1234)
                .build();
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
                .dailyCost(new BigDecimal("111.11"))
                .status(Status.AVAILABLE)
                .build();

    }

    private Rental initRental() {
        User user = initUser();
        Car car = initCar();

        return Rental.builder()
                .id(1L)
                .rentedFrom(LocalDate.of(2022, 1, 1))
                .rentedUntil(LocalDate.of(2022, 1, 1))
                .cost(new BigDecimal(100))
                .user(user)
                .car(car)
                .build();
    }

    private RentalDto initRentalDto() {
        return RentalDto.builder()
                .id(1L)
                .rentedFrom(LocalDate.of(2022, 1, 1))
                .rentedUntil(LocalDate.of(2022, 1, 1))
                .carId(1L)
                .userId(1L)
                .build();
    }
}
