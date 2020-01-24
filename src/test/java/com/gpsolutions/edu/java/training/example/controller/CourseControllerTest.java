package com.gpsolutions.edu.java.training.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Wladimir Litvinov
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CourseControllerTest extends AbstractControllerTest {

    @Test
    public void testGetCourseListIsOk() throws Exception {
        // given
        final String token = signInAsStudent();
        // when
        mockMvc.perform(get("/course").header("Authorization", token))
            // then
            .andExpect(status().isOk())
            .andExpect(content().json("[\n" +
                                      "  {\n" +
                                      "    \"id\" : 1, \n" +
                                      "    \"title\" : \"GP Java Training Winter 2019-2020\",\n" +
                                      "    \"description\" : \"Курс по обучению старту проектов на языке Java\",\n" +
                                      "    \"startDate\" : \"04.02.2020\", \n" +
                                      "    \"endDate\" : \"28.02.2020\",\n" +
                                      "    \"teacherName\" : \"Литвинов Владимир Дмитриевич\" \n" +
                                      "  }\n" +
                                      "]"));
    }
}
