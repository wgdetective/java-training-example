package com.gpsolutions.edu.java.training.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

import static org.hamcrest.Matchers.hasLength;
import static org.mockito.BDDMockito.willReturn;
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
    public void testStudentSignUpWhenUserAlreadyExisted() throws Exception {
        // given
        signInAsStudent();
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
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testStudentSignInIsOk() throws Exception {
        // given
        final User user = new User("vasya@email.com", passwordEncoder.encode("qwerty"),
                                   List.of(new SimpleGrantedAuthority("STUDENT")));
        willReturn(user).given(loadUserDetailService).loadUserByUsername("vasya@email.com");
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

    @Test
    public void testStudentSignInWithWrongPassword() throws Exception {
        // given
        final User user = new User("vasya@email.com", passwordEncoder.encode("qwerty"),
                                   List.of(new SimpleGrantedAuthority("STUDENT")));
        willReturn(user).given(loadUserDetailService).loadUserByUsername("vasya@email.com");
        // when
        mockMvc.perform(post("/student/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                     "  \"email\" : \"vasya@email.com\",\n" +
                                     "  \"password\" : \"wrongPassword\"\n" +
                                     "}"))
            // then
            .andExpect(status().isForbidden());
    }

    @Test
    public void testStudentSignInWithWrongEmail() throws Exception {
        // given
        final User user = new User("vasya@email.com", passwordEncoder.encode("qwerty"),
                                   List.of(new SimpleGrantedAuthority("STUDENT")));
        willReturn(user).given(loadUserDetailService).loadUserByUsername("vasya@email.com");
        // when
        mockMvc.perform(post("/student/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                     "  \"email\" : \"noSuchVasyavasya@email.com\",\n" +
                                     "  \"password\" : \"wrongPassword\"\n" +
                                     "}"))
            // then
            .andExpect(status().isForbidden());
    }
}
