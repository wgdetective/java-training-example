package com.gpsolutions.edu.java.training.example.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gpsolutions.edu.java.training.example.dto.Course;
import com.gpsolutions.edu.java.training.example.service.CourseService;
import com.gpsolutions.edu.java.training.example.service.StudentOnCourseService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;

/**
 * @author Wladimir Litvinov
 */
public class StudentControllerTest extends AbstractControllerTest {

    @SpyBean
    private StudentOnCourseService studentOnCourseService;
    @SpyBean
    private CourseService courseService;

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

    @Test
    public void testRegisterOfStudentForCourseWhenThereIsNoSuchCourse() throws Exception {
        // given
        final String token = signInAsStudent();
        // when
        mockMvc.perform(get("/student/register/course/2").header("Authorization", token))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("No course with id=2 was found"));
    }

    @Test
    public void testRegisterOfStudentForCourseWhenCourseIsEnded() throws Exception {
        // given
        final String token = signInAsStudent();
        given(courseService.getCourse(3L))
                .willReturn(Optional.of(Course.builder().endDate(LocalDate.now().minusDays(1)).build()));
        // when
        mockMvc.perform(get("/student/register/course/3").header("Authorization", token))
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorMessage").value("Course has already ended"));
        ;
    }
}
