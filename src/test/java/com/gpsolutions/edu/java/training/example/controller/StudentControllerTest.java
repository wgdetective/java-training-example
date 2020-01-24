package com.gpsolutions.edu.java.training.example.controller;

import com.gpsolutions.edu.java.training.example.service.StudentOnCourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Wladimir Litvinov
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private StudentOnCourseService studentOnCourseService;

    @Test
    public void testRegisterOfStudentForCourse() throws Exception {
        // given
        final String token = signInAsStudent();
        // when
        mockMvc.perform(get("/student/register/course/1").header("Authorization", token))
            // then
            .andExpect(status().isOk());
        verify(studentOnCourseService, only()).registerOnCourse("vasya@email.com", 1L);
    }
}
