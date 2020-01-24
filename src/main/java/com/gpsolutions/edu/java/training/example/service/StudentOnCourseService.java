package com.gpsolutions.edu.java.training.example.service;

import com.gpsolutions.edu.java.training.example.dto.Course;
import com.gpsolutions.edu.java.training.example.exception.BadCourseOperationException;
import com.gpsolutions.edu.java.training.example.exception.NoSuchCourseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Wladimir Litvinov
 */
@Log
@Service
@RequiredArgsConstructor
public class StudentOnCourseService {

    private final CourseService courseService;

    public void registerOnCourse(final String email, final Long courseId)
        throws NoSuchCourseException, BadCourseOperationException {
        log.info(String.format("Registration of student (%s) on course (%d)", email, courseId));
        final Optional<Course> course = courseService.getCourse(courseId);
        if (course.isEmpty()) {
            throw new NoSuchCourseException("No course with id=" + courseId + " was found");
        } else if (LocalDate.now().isAfter(course.get().getEndDate())) {
            throw new BadCourseOperationException("Course has already ended");
        }
    }
}
