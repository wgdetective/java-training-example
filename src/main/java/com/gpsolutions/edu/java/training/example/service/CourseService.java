package com.gpsolutions.edu.java.training.example.service;

import com.gpsolutions.edu.java.training.example.dto.Course;
import com.gpsolutions.edu.java.training.example.mapper.CourseMapper;
import com.gpsolutions.edu.java.training.example.repository.CourseRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author Wladimir Litvinov
 */
@Data
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @PostConstruct
    public void init() {
        courseRepository.save(courseMapper.sourceToDestination(Course.builder()
                .id(1L)
                .title("GP Java Training Winter 2019-2020")
                .description("Курс по обучению старту проектов на языке Java")
                .startDate(LocalDate.of(2020, 2, 4))
                .endDate(LocalDate.of(2020, 2, 28))
                .teacherName("Литвинов Владимир Дмитриевич")
                .build()));
    }

    public List<Course> getList() {
        return courseRepository.findAll().stream().map(courseMapper::destinationToSource).collect(Collectors.toList());
    }

    public Optional<Course> getCourse(final Long id) {
        return courseRepository.findById(id).map(courseMapper::destinationToSource);
    }
}
