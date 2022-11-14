package com.kodilla.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {

    private Long id;
    private LocalDate rentedFrom;
    private LocalDate rentedUntil;
    private Long carId;
    private Long userId;

}