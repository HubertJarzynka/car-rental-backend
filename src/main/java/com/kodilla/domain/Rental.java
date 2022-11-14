package com.kodilla.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "RENTED_FROM")
    private LocalDate rentedFrom;
    @NotNull
    @Column(name = "RENTED_UNTIL")
    private LocalDate rentedUntil;

    @NotNull
    @Column(name = "RENTED_DAYS")
    private Long daysRented;

    @NotNull
    @Column(name = "COST")
    private BigDecimal cost;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

}
