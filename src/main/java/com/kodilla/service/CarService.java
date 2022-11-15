package com.kodilla.service;

import com.kodilla.domain.Car;
import com.kodilla.exception.CarNotFoundException;
import com.kodilla.repository.CarRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    public Car getCarById(final Long id) throws CarNotFoundException {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    public void deleteCar(final Long id) {
        carRepository.deleteById(id);
    }

    public Car saveCar(final Car car) {
        return carRepository.save(car);
    }

    public List<Car> getCarsByBrand(final String brand) throws CarNotFoundException {
        return carRepository.findAllByBrand(brand);
    }

    public List<Car> getCarsByProductionYear(final int year) {
        return carRepository.findAllByProductionYear(year);
    }

    public List<Car> getCarsByMileage(final int mileage) {
        return carRepository.findAllByMileage(mileage);
    }

    public List<Car> getCarsByFuelType(final String fuel) {
        return carRepository.findAllByFuel(fuel);
    }

    public List<Car> getCarsByDailyCost(final BigDecimal cost) {
        return carRepository.findAllByDailyCost(cost);
    }

}