package com.kodilla.service;

import com.kodilla.domain.User;
import com.kodilla.dto.UserDto;
import com.kodilla.exception.UserNotFoundException;
import com.kodilla.mapper.UserMapper;
import com.kodilla.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getUserByID(final Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByMail(String mail) throws UserNotFoundException{
        return userRepository.findByMail(mail).orElseThrow(UserNotFoundException::new);
    }

    public User getUserByPhoneNumber(int phoneNumber) throws UserNotFoundException{
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User saveUser(final UserDto userDto) {
        return userRepository.save(userMapper.mapToUser(userDto));
    }

    public Boolean isEmailExist(String email) {
        return userRepository.existsByMail(email);
    }

}