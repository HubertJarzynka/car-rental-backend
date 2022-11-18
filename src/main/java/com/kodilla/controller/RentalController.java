package com.kodilla.controller;

import com.kodilla.dto.FullRentalDto;
import com.kodilla.dto.RentalDto;
import com.kodilla.exception.CarNotFoundException;
import com.kodilla.exception.RentalNotFoundException;
import com.kodilla.exception.UserNotFoundException;
import com.kodilla.facade.RentalFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalFacade rentalFacade;

    @GetMapping
    public List<FullRentalDto> getAllRentals() {
        return rentalFacade.getAllRentals();
    }

    @GetMapping("/{id}")
    public FullRentalDto getRentalById(@PathVariable Long id) throws RentalNotFoundException {
        return rentalFacade.getRentalById(id);
    }

    @PostMapping
    public FullRentalDto createRental(@RequestBody RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        return rentalFacade.createRental(rentalDto);
    }

    @PutMapping
    public FullRentalDto updateRental(@RequestBody RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException, RentalNotFoundException {
        return rentalFacade.updateRental(rentalDto);
    }

    @DeleteMapping("/{id}")
    public void closeRental(@PathVariable Long id) throws RentalNotFoundException {
        rentalFacade.closeRental(id);
    }
}