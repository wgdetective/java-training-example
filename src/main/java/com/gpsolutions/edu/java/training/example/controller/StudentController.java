package com.gpsolutions.edu.java.training.example.controller;

import com.gpsolutions.edu.java.training.example.dto.StudentSignUpRequest;
import com.gpsolutions.edu.java.training.example.dto.UserSignInRequest;
import com.gpsolutions.edu.java.training.example.service.StudentOnCourseService;
import com.gpsolutions.edu.java.training.example.service.StudentService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wladimir Litvinov
 */
@Log
@Data
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentOnCourseService studentOnCourseService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String singUp(@RequestBody final StudentSignUpRequest request) {
        return studentService.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String singIn(@RequestBody final UserSignInRequest request) {
        return studentService.signIn(request);
    }

    @GetMapping(value = "/register/course/{courseId}")
    public void register(@PathVariable final Long courseId,
                         @RequestHeader final Long studentId) {
        studentOnCourseService.registerOnCourse(studentId, courseId);
    }
}
