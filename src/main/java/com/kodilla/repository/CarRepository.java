package com.kodilla.repository;

import com.kodilla.domain.Vehicle;
import com.kodilla.dto.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Vehicle, Long> {

    @Override
    List<Vehicle> findAll();

    List<Vehicle> findAllByBrand(String brand);

    List<Vehicle> findAllByProductionYear(int productionYear);

    List<Vehicle> findAllByMileage(int mileage);

    List<Vehicle> findAllByFuel(String fuel);

    List<Vehicle> findAllByDailyCost(double cost);
}
