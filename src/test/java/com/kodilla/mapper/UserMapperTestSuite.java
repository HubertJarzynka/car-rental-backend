package com.kodilla.mapper;

import com.kodilla.domain.User;
import com.kodilla.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserMapperTestSuite {

    @InjectMocks
    private UserMapper userMapper;

    @Test
    void mapToUserTest() {
        //Given
        UserDto userDto = initUserDto();
        //When
        User user = userMapper.mapToUser(userDto);
        //Then
        assertEquals("Janusz", user.getName());
        assertEquals("Kowalski", user.getSurname());
        assertEquals("password", user.getPassword());
        assertEquals("janusz.kowalski@gmail.com", user.getMail());
        assertEquals(123456789, user.getPhoneNumber());
    }

    @Test
    void mapToUserDtoTest() {
        //Given
        User user = initUser();
        //When
        UserDto userDto = userMapper.mapToUserDto(user);
        //Then
        assertEquals("Janusz", userDto.getName());
        assertEquals("Kowalski", userDto.getSurname());
        assertEquals("password", userDto.getPassword());
        assertEquals("janusz.kowalski@gmail.com", userDto.getMail());
        assertEquals(123456789, userDto.getPhoneNumber());
    }

    @Test
    void mapToUserDtoListTest() {
        //Given
        User user = initUser();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        //When
        List<UserDto> userDtos = userMapper.mapToUserDtoList(userList);
        //Then
        assertEquals(1, userDtos.size());
        assertEquals("Janusz", userDtos.get(0).getName());
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

    private UserDto initUserDto() {
        return UserDto.builder()
                .id(1L)
                .name("Janusz")
                .surname("Kowalski")
                .password("password")
                .mail("janusz.kowalski@gmail.com")
                .phoneNumber(123456789)
                .build();
    }

}
