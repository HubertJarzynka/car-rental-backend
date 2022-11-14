package com.kodilla.mapper;

import com.kodilla.domain.User;
import com.kodilla.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .mail(userDto.getMail())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }

    public UserDto mapToUserDto(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .password(user.getPassword())
                .mail(user.getMail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}