package com.kodilla.facade;

import com.kodilla.dto.UserDto;
import com.kodilla.exception.UserNotFoundException;
import com.kodilla.mapper.UserMapper;
import com.kodilla.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserDto getUserById(final Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByID(id));
    }

    public List<UserDto> getAllUsers(){
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    public UserDto getUserByMail(String mail) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByMail(mail));
    }

    public UserDto getUserByPhoneNumber(int number) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByPhoneNumber(number));
    }

    public Boolean isEmailExist(String mail) {
        return userService.isEmailExist(mail);
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public UserDto saveUser(final UserDto userDto) {
        return userMapper.mapToUserDto(userService.saveUser(userDto));
    }

}