package com.kodilla.mapper;

import com.kodilla.domain.Car;
import com.kodilla.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CarMapper {

    public Car mapToCar(final CarDto carDto) {
        return Car.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .productionYear(carDto.getProductionYear())
                .vin(carDto.getVin())
                .mileage(carDto.getMileage())
                .fuel(carDto.getFuel())
                .dailyCost(carDto.getDailyCost())
                .status(carDto.getStatus())
                .build();
    }

    public CarDto mapToCarDto(final Car car){
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .vin(car.getVin())
                .productionYear(car.getProductionYear())
                .mileage(car.getMileage())
                .fuel(car.getFuel())
                .dailyCost(car.getDailyCost())
                .status(car.getStatus())
                .build();
    }

    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }
}