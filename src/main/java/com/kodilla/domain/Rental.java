package com.kodilla.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "RENTED_FROM")
    private LocalDate rentedFrom;

    @NotNull
    @Column(name = "RENTED_UNTIL")
    private LocalDate rentedUntil;

    @NotNull
    @Column(name = "COST")
    private BigDecimal cost;

}
