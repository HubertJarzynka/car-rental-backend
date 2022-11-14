package com.kodilla.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullRentalDto {

    private Long id;
    private LocalDate rentedFrom;
    private LocalDate rentedUntil;
    private Long carId;
    private String carBrand;
    private String carModel;
    private String userName;
    private String userSurname;
    private String userMail;
    private int userPhoneNumber;
}