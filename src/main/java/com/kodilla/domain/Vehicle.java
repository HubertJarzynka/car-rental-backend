package com.kodilla.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VEHICLE")

public class Vehicle {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "BRAND")
    private String brand;

    @NotNull
    @Column(name = "MODEL")
    private String model;

    @NotNull
    @Column(name = "PRODUCTION_YEAR")
    private int productionYear;

    @NotNull
    @Column(name = "MILEAGE")
    private int mileage;

    @NotNull
    @Column(name = "FUEL")
    private String fuel;

    @NotNull
    @Column(name = "DAILY_COST")
    private double dailyCost;

    @NotNull
    @Column(name = "Car_STATUS")
    private Status status;
}
