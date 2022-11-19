package com.kodilla.service;

import com.kodilla.domain.Login;
import com.kodilla.exception.LoginNotFoundException;
import com.kodilla.repository.LoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class LoginServiceTestSuite {
    @InjectMocks
    private LoginService loginService;

    @Mock
    private LoginRepository loginRepository;

    @Test
    void isLoginRegisteredTest(){
        //Given
        when(loginRepository.existsByEmailAndPassword("email", "password")).thenReturn(true);

        //When
        Boolean result = loginService.isLoginRegistered("email", "password");

        assertTrue(result);
    }

    @Test
    void shouldGetLoginByEmailAndPassword() throws LoginNotFoundException {
        //Given
        Login login = initLogin();
        when(loginRepository.findByEmailAndPassword("email", "password")).thenReturn(Optional.of(login));

        //When
        Login result = loginService.getLoginByEmailAndPassword("email", "password");

        //Then
        assertEquals("email", result.getEmail());
        assertEquals("password", result.getPassword());
    }

    @Test
    void shouldSaveLogin() {
        //Given
        Login login = initLogin();

        //When
        loginService.saveLogin(login);

        //Then
        verify(loginRepository, times(1)).save(login);
    }

    @Test
    void shouldDeleteLogin() {
        //Given
        Login login = initLogin();

        //When
        loginService.deleteLogin(login);

        //Then
        verify(loginRepository, times(1)).delete(login);
    }

    private Login initLogin() {
        return new Login(
                1L,
                "email",
                "password"
        );
    }
}
