package com.kodilla.facades;
import com.kodilla.domain.Login;
import com.kodilla.domain.User;
import com.kodilla.dto.EmailVerificationDto;
import com.kodilla.dto.UserDto;
import com.kodilla.exception.LoginNotFoundException;
import com.kodilla.exception.UserNotFoundException;
import com.kodilla.facade.UserFacade;
import com.kodilla.mapper.UserMapper;
import com.kodilla.service.EmailVerificationService;
import com.kodilla.service.LoginService;
import com.kodilla.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(SpringExtension.class)
public class UserFacadeTestSuite {

    @InjectMocks
    private UserFacade userFacade;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserService userService;

    @Mock
    private LoginService loginService;

    @Mock
    private EmailVerificationService emailVerificationService;

    @Test
    void shouldGetAllUsers() {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();

        List<User> userList = Collections.singletonList(user);
        List<UserDto> userDtoList = Collections.singletonList(userDto);

        when(userService.getAllUsers()).thenReturn(userList);
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);

        //When
        List<UserDto> resultList = userFacade.getAllUsers();

        //Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
        resultList.forEach(u -> {
            assertEquals(u.getId(), userDto.getId());
            assertEquals(u.getName(), userDto.getName());
            assertEquals(u.getPhoneNumber(), userDto.getPhoneNumber());
            assertEquals(u.getPassword(), userDto.getPassword());
        });
    }

    @Test
    void shouldGetUserById() throws UserNotFoundException {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();
        when(userFacade.getUserById(1L)).thenReturn(userDto);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        //When
        UserDto result = userFacade.getUserById(1L);
        //Then
        assertEquals(userDto.getId(), result.getId());
    }

    @Test
    void shouldGetUserByEmailTest() throws UserNotFoundException {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();

        when(userService.getUserByMail("mail")).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto result = userFacade.getUserByMail("mail");

        //Then
        assertEquals(userDto.getMail(), result.getMail());
    }

    @Test
    void shouldGetUserByPhoneNumberTest() throws UserNotFoundException {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();

        when(userService.getUserByPhoneNumber(1234)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        //When
        UserDto result = userFacade.getUserByPhoneNumber(1234);

        //Then
        assertEquals(userDto.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    void doesUserWithMailExistTest() {
        //Given
        when(userFacade.isEmailExist("mail")).thenReturn(true);
        //When
        boolean result = userFacade.isEmailExist("mail");
        //Then
        assertTrue(result);
    }

    @Test
    void shouldSaveUser() {
        //Given
        User user = initUser();
        UserDto userDto = initUserDto();
        EmailVerificationDto emailVerificationDto = initEmailVerificationDto();

        when(emailVerificationService.verifyEmail(any())).thenReturn(emailVerificationDto);
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.saveUser(userDto)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        doNothing().when(loginService).saveLogin(ArgumentMatchers.any());

        //When
        UserDto savedUser = userFacade.saveUser(userDto);

        //Then
        assertEquals(userDto.getId(), savedUser.getId());
        assertEquals(userDto.getName(), savedUser.getName());
    }

    @Test
    void shouldDeleteUser() throws LoginNotFoundException, UserNotFoundException {
        //Given
        User user = initUser();
        Login login = initLogin();

        when(userService.getUserByID(anyLong())).thenReturn(user);
        when(loginService.getLoginByEmailAndPassword(anyString(), anyString())).thenReturn(login);
        doNothing().when(loginService).deleteLogin(any());

        //When
        userFacade.deleteUser(2L);

        //Then
        verify(userService, times(1)).deleteUser(2L);
    }

    private User initUser() {
        return User.builder()
                .id(1L)
                .name("Jan")
                .surname("Kowalski")
                .password("password")
                .mail("mail")
                .phoneNumber(1234)
                .build();
    }

    private UserDto initUserDto() {
        return UserDto.builder()
                .id(1L)
                .name("Jan")
                .surname("Kowalski")
                .password("password")
                .mail("mail")
                .phoneNumber(1234)
                .build();
    }

    private EmailVerificationDto initEmailVerificationDto() {
        return new EmailVerificationDto("true", "true", "true");
    }

    private Login initLogin() {
        return new Login(
                1L,
                "email",
                "password"
        );
    }
}