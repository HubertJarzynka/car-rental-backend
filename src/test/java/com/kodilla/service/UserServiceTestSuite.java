package com.kodilla.service;

import com.kodilla.domain.User;
import com.kodilla.exception.UserNotFoundException;
import com.kodilla.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class UserServiceTestSuite {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldFetAllUsers() {
        //Given
        User user = initUser();
        List<User> userList = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(userList);

        //When
        List<User> resultList = userService.getAllUsers();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
    }


    @Test
    public void getUserByIdTest() throws UserNotFoundException {
        //Given
        User user = initUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //When
        User result = userService.getUserByID(1L);

        //Then
        assertEquals(user.getId(), result.getId());
    }

    @Test
    void shouldGetUserByMail() throws UserNotFoundException {
        //Given
        User user = initUser();
        when(userRepository.findByMail("mail")).thenReturn(Optional.of(user));

        //When
        User result = userService.getUserByMail("mail");

        //Then
        assertEquals(user.getMail(), result.getMail());
    }

    @Test
    void shouldGetUserByPhoneNumber() throws UserNotFoundException {
        //Given
        User user = initUser();
        when(userRepository.findByPhoneNumber(1234)).thenReturn(Optional.of(user));

        //When
        User result = userService.getUserByPhoneNumber(1234);

        //Then
        assertEquals(user.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    void shouldDeleteUser() {
        //Given
        //When
        userService.deleteUser(2L);

        //Then
        verify(userRepository, times(1)).deleteById(2L);
    }


    @Test
    void doesMailExistTest() {
        //Given
        when(userRepository.existsByMail("mail")).thenReturn(true);
        //When
        Boolean result = userService.isEmailExist("mail");
        //Then
        assertTrue(result);
    }

    private User initUser() {
        return User.builder()
                .id(1L)
                .name("Janusz")
                .surname("Kowalski")
                .password("password")
                .mail("janusz.kowalski@gmail.com")
                .phoneNumber(123456789)
                .build();
    }

}
