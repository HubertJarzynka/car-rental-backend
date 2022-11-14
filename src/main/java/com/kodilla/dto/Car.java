package com.kodilla.dto;

import com.kodilla.domain.Status;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Car {
    private Long id;
    private String brand;
    private String model;
    private int productionYear;
    private int mileage;
    private String fuel;
    private double dailyCost;
    private Status status;
}
