package com.kodilla.mapper;

import com.kodilla.domain.Rental;
import com.kodilla.dto.FullRentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalMapper {

    public FullRentalDto mapToFullRentalDto(final Rental rental) {
        return FullRentalDto.builder()
                .id(rental.getId())
                .rentedFrom(rental.getRentedFrom())
                .rentedUntil(rental.getRentedUntil())
                .cost(rental.getCost())
                .carId(rental.getCar().getId())
                .carBrand(rental.getCar().getBrand())
                .carModel(rental.getCar().getModel())
                .userName(rental.getUser().getName())
                .userSurname(rental.getUser().getSurname())
                .userMail(rental.getUser().getMail())
                .userPhoneNumber(rental.getUser().getPhoneNumber())
                .build();
    }

    public List<FullRentalDto> mapToFullRentalDtoList(final List<Rental> rentalList) {
        return rentalList.stream()
                .map(this::mapToFullRentalDto)
                .collect(Collectors.toList());
    }
}