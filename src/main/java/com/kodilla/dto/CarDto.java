package com.kodilla.dto;

import com.kodilla.domain.Status;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CarDto {
    private BigDecimal dailyCost;
    private Status status;
    private Long id;
    private String brand;
    private String model;
    private String vin;
    private int productionYear;
    private int mileage;
    private String fuel;
}
