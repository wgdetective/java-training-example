package com.gpsolutions.edu.java.training.example.service;

import com.gpsolutions.edu.java.training.example.dto.Course;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Wladimir Litvinov
 */
@Service
public class CourseService {
    public List<Course> getList() {
        return List.of(Course.builder()
                           .id(1L)
                           .title("GP Java Training Winter 2019-2020")
                           .description("Курс по обучению старту проектов на языке Java")
                           .startDate(LocalDate.of(2020, 2, 4))
                           .endDate(LocalDate.of(2020, 2, 28))
                           .teacherName("Литвинов Владимир Дмитриевич")
                           .build());
    }
}
