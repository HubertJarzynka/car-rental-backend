package com.kodilla.service;

import com.kodilla.domain.Car;
import com.kodilla.dto.CarDto;
import com.kodilla.exception.CarNotFoundException;
import com.kodilla.mapper.CarMapper;
import com.kodilla.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public Car getCar(final Long id) throws CarNotFoundException {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public CarDto getCarById(Long id) throws CarNotFoundException {
        return carMapper.mapToCarDto(carRepository.findById(id).orElseThrow(CarNotFoundException::new));
    }

    public void deleteCar(final Long id) {
        carRepository.deleteById(id);
    }

    public Car saveCar(final CarDto carDto) {
        return carRepository.save(carMapper.mapToCar(carDto));
    }

    public List<CarDto> getCarsByBrand(String brand) throws CarNotFoundException {
        return carMapper.mapToCarDtoList(carRepository.findAllByBrand(brand));
    }

    public List<CarDto> getCarsByProductionYear(int production) {
        return carMapper.mapToCarDtoList(carRepository.findAllByProductionYear(production));
    }

    public List<CarDto> getCarsByMileage(int mileage) {
        return carMapper.mapToCarDtoList(carRepository.findAllByMileage(mileage));
    }

    public List<CarDto> getCarsByFuelType(String fuel) {
        return carMapper.mapToCarDtoList(carRepository.findAllByFuel(fuel));
    }

    public List<CarDto> getCarsByDailyCost(double cost) {
        return carMapper.mapToCarDtoList(carRepository.findAllByDailyCost(cost));
    }
}