package com.gpsolutions.edu.java.training.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Wladimir Litvinov
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCourseListIsOk() throws Exception {
        // given
        // when
        mockMvc.perform(get("/course/list"))
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

    @Test
    public void testRegisterOfStudentForCourse() throws Exception {
        // given
        // when
        mockMvc.perform(get("/course/1/register")
                            .header("userId", 1))
            // then
            .andExpect(status().isOk());
    }
}
