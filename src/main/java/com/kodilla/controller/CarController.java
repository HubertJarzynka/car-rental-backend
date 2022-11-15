package com.kodilla.controller;

import com.kodilla.dto.CarDto;
import com.kodilla.exception.CarNotFoundException;
import com.kodilla.facade.CarFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarFacade carFacade;

    @GetMapping
    public List<CarDto> getAllCars() {
        return carFacade.getAllCars();
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) throws CarNotFoundException {
        return carFacade.getCarById(id);
    }

    @GetMapping("/brand/{brand}")
    public List<CarDto> getCarsByBrand(@PathVariable String brand) throws CarNotFoundException {
        return carFacade.getCarsByBrand(brand);
    }

    @GetMapping("/year/{productionYear}")
    public List<CarDto> getCarsByProductionYear(@PathVariable int productionYear) throws CarNotFoundException {
        return carFacade.getCarsByProductionYear(productionYear);
    }

    @GetMapping("/mileage/{mileage}")
    public List<CarDto> getCarsByMileage(@PathVariable int mileage) throws CarNotFoundException {
        return carFacade.getCarsByMileage(mileage);
    }

    @GetMapping("/fuel/{fuel}")
    public List<CarDto> getCarsByFuelType(@PathVariable String fuel) throws CarNotFoundException {
        return carFacade.getCarsByFuelType(fuel);
    }

    @GetMapping("/daily_cost/{cost}")
    public List<CarDto> getCarsByDailyCost(@PathVariable BigDecimal cost) throws  CarNotFoundException{
        return carFacade.getCarsByDailyCost(cost);
    }

    @PostMapping
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carFacade.saveCar(carDto);
    }

    @PutMapping
    public CarDto updateCar(@RequestBody CarDto carDto) {
        return carFacade.saveCar(carDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCar(@PathVariable Long id) {
        carFacade.deleteCar(id);
    }

}