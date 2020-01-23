package com.gpsolutions.edu.java.training.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Wladimir Litvinov
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
                                     "  \"gender\" : \"male\", \n" +
                                     "  \"birthDate\" : \"19.01.1995\",\n" +
                                     "  \"info\" : \"Молодой инженер\" \n" +
                                     "}"))
            // then
            .andExpect(status().isCreated())
            .andExpect(content().json("{\n" +
                                      "  \"id\" : 1\n" +
                                      "}"));
    }

    @Test
    public void testStudentSignInIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(post("/student/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                     "  \"email\" : \"vasya@email.com\",\n" +
                                     "  \"password\" : \"qwerty\"\n" +
                                     "}"))
            // then
            .andExpect(status().isOk())
            .andExpect(content().json("{\n" +
                                      "  \"id\" : 1\n" +
                                      "}"));
    }
}
