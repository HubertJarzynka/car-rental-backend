package com.kodilla.service;

import com.kodilla.config.AdminConfig;
import com.kodilla.domain.Status;
import com.kodilla.repository.CarRepository;
import com.kodilla.repository.RentalRepository;
import com.kodilla.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDailyMailMessageService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final AdminConfig adminConfig;

    public String emailBodyCreate() {
        long userRepositorySize = userRepository.count();
        long carRentedSize = carRepository.countAllByStatus(Status.RENTED);
        long carAvailableSize = carRepository.countAllByStatus(Status.AVAILABLE);
        long rentalRepositorySize = rentalRepository.count();

        return ("\n Dear Administrator." + adminConfig.getAdminName() +
                "\n\t Below there are daily statistics considering your page: \n" +
                "\n\t number of registered users: " + userRepositorySize +
                "\n\t number of rented cars: " + carRentedSize +
                "\n\t number of available cars: " + carAvailableSize +
                "\n\t number of all rentals: " + rentalRepositorySize + "\n" +
                "\n //Car Rental//");
    }
}