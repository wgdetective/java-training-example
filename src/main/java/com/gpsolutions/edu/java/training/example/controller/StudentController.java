package com.gpsolutions.edu.java.training.example.controller;

import com.gpsolutions.edu.java.training.example.exception.BadCourseOperationException;
import com.gpsolutions.edu.java.training.example.exception.NoSuchCourseException;
import com.gpsolutions.edu.java.training.example.service.StudentOnCourseService;
import com.gpsolutions.edu.java.training.example.service.StudentService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wladimir Litvinov
 */
@Log
@Data
@RestController
@RequestMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
public class StudentController {

    private final StudentService studentService;
    private final StudentOnCourseService studentOnCourseService;

    @GetMapping(value = "/register/course/{courseId}")
    public void register(@PathVariable final Long courseId,
                         final Authentication authentication)
        throws BadCourseOperationException, NoSuchCourseException {
        studentOnCourseService.registerOnCourse(authentication.getName(), courseId);
    }
}
