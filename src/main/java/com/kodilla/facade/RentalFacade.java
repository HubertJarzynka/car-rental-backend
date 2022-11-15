package com.kodilla.facade;

import com.kodilla.dto.FullRentalDto;
import com.kodilla.dto.RentalDto;
import com.kodilla.exception.CarNotFoundException;
import com.kodilla.exception.RentalNotFoundException;
import com.kodilla.exception.UserNotFoundException;
import com.kodilla.mapper.RentalMapper;
import com.kodilla.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RentalFacade {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    public FullRentalDto getRentalById(final Long id) throws RentalNotFoundException {
        return rentalMapper.mapToFullRentalDto(rentalService.getRentalById(id));
    }

    public List<FullRentalDto> getAllRentals() {
        return rentalMapper.mapToFullRentalDtoList(rentalService.getAllRentals());
    }

    public FullRentalDto createRental(final RentalDto rentalDto) throws UserNotFoundException, CarNotFoundException {
        return rentalMapper.mapToFullRentalDto(rentalService.createRental(rentalDto));
    }

    public FullRentalDto updateRental(final RentalDto rentalDto) throws UserNotFoundException, RentalNotFoundException, CarNotFoundException {
        return rentalMapper.mapToFullRentalDto(rentalService.updateRental(rentalDto));
    }

    public void closeRental(Long id) throws RentalNotFoundException {
        rentalService.closeRental(id);
    }


}