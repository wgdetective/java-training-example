package com.gpsolutions.edu.java.training.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Wladimir Litvinov
 */
public class AuthControllerTest extends AbstractControllerTest {

    @Test
    public void testStudentSignUpIsCreated() throws Exception {
        // given
        // when
        mockMvc.perform(post("/student/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                     "  \"email\" : \"vasya@email.com\",\n" +
                                     "  \"password\" : \"qwerty\",\n" +
                                     "  \"fio\" : \"Пупкин Василий Иванович\",\n" +
                                     "  \"gender\" : \"MALE\", \n" +
                                     "  \"birthDate\" : \"19.01.1995\",\n" +
                                     "  \"info\" : \"Молодой инженер\" \n" +
                                     "}"))
            // then
            .andExpect(status().isCreated())
            .andExpect(jsonPath("token", hasLength(144)));
    }

    @Test
    public void testStudentSignInIsOk() throws Exception {
        // given
        final User user = new User("vasya@email.com", passwordEncoder.encode("qwerty"),
                                   List.of(new SimpleGrantedAuthority("STUDENT")));
        given(loadUserDetailService.loadUserByUsername("vasya@email.com"))
            .willReturn(user);
        // when
        mockMvc.perform(post("/student/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                     "  \"email\" : \"vasya@email.com\",\n" +
                                     "  \"password\" : \"qwerty\"\n" +
                                     "}"))
            // then
            .andExpect(status().isOk())
            .andExpect(jsonPath("token", hasLength(144)));
    }
}
