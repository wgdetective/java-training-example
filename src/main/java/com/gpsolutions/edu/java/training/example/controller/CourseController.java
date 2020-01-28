package com.gpsolutions.edu.java.training.example.controller;

import com.gpsolutions.edu.java.training.example.dto.Course;
import com.gpsolutions.edu.java.training.example.service.CourseService;
import java.util.List;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wladimir Litvinov
 */
@Log
@Data
@RestController
@RequestMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<Course> getList() {
        return courseService.getList();
    }
}
