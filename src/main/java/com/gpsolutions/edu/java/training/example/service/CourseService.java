package com.gpsolutions.edu.java.training.example.service;

import com.gpsolutions.edu.java.training.example.dto.Course;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Wladimir Litvinov
 */
@Service
public class CourseService {
    private final List<Course> courses = List.of(Course.builder()
                                                     .id(1L)
                                                     .title("GP Java Training Winter 2019-2020")
                                                     .description("Курс по обучению старту проектов на языке Java")
                                                     .startDate(LocalDate.of(2020, 2, 4))
                                                     .endDate(LocalDate.of(2020, 2, 28))
                                                     .teacherName("Литвинов Владимир Дмитриевич")
                                                     .build());

    public List<Course> getList() {
        return courses;
    }

    public Optional<Course> getCourse(final Long id) {
        return courses.stream().filter(c -> c.getId().equals(id)).findAny();
    }
}
