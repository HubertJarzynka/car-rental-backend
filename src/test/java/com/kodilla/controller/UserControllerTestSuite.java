package com.kodilla.controller;

import com.kodilla.dto.UserDto;
import com.kodilla.facade.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTestSuite {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    @Test
    void shouldFetchUserByPhoneNumber() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        when(userFacade.getUserByPhoneNumber(123456789)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/users/phone/123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("phoneNumber", String.valueOf(123456789)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.phoneNumber", is(123456789)));
    }

    @Test
    void shouldFetchUserByEmail() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        when(userFacade.getUserByMail("janusz.kowalski@gmail.com")).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/users/mail/janusz.kowalski@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("email", "janusz.kowalski@gmail.com"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.mail", is("janusz.kowalski@gmail.com")));
    }

    @Test
    void shouldFetchUserById() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        when(userFacade.getUserById(1L)).thenReturn(userDto);
        //When & Then
        mockMvc.perform(get("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Janusz")));
    }

    @Test
    void shouldFetchAllUsers() throws Exception {
        //Given
        UserDto userDto = createSampleUser();
        List<UserDto> userDtoList = Collections.singletonList(userDto);
        when(userFacade.getAllUsers()).thenReturn(userDtoList);
        //When & Then
        mockMvc.perform(get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Janusz")))
                .andExpect(jsonPath("$[0].phoneNumber", is(123456789)));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("id", "1"))
                .andExpect(status().is(200));
    }

    private UserDto createSampleUser() {
        return UserDto.builder()
                .id(1l)
                .name("Janusz")
                .surname("Kowalski")
                .password("password")
                .mail("janusz.kowalski@gmail.com")
                .phoneNumber(123456789)
                .build();
    }
}
