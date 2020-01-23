package com.gpsolutions.edu.java.training.example.controller;

import com.gpsolutions.edu.java.training.example.service.StudentOnCourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @SpyBean
    private StudentOnCourseService studentOnCourseService;

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

    @Test
    public void testRegisterOfStudentForCourse() throws Exception {
        // given
        // when
        mockMvc.perform(get("/student/register/course/1")
                            .header("studentId", 1))
            // then
            .andExpect(status().isOk());
        verify(studentOnCourseService, only()).registerOnCourse(1L, 1L);
    }
}
