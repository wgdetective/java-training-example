package com.gpsolutions.edu.java.training.example.controller;

import com.gpsolutions.edu.java.training.example.dto.Course;
import com.gpsolutions.edu.java.training.example.service.CourseService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wladimir Litvinov
 */
@Log
@Data
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping(value = "/list")
    public List<Course> getList() {
        return courseService.getList();
    }
}
