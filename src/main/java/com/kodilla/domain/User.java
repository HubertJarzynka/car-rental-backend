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
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAMES")
    private String name;

    @NotNull
    @Column(name = "SURNAMES")
    private String surname;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "MAIL", unique = true)
    private String mail;

    @NotNull
    @Column(name = "PHONE_NUMBER")
    private int phoneNumber;


}
