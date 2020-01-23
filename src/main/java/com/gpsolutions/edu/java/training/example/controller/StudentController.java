package com.gpsolutions.edu.java.training.example.controller;

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
@RestController
@RequestMapping("/student")
@Log
public class StudentController {

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String singUp(@RequestBody final String request) {
        return "{\"id\":1}";
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String singIn(@RequestBody final String request) {
        return "{\"id\":1}";
    }

    @GetMapping(value = "/register/course/{courseId}")
    public void register(@PathVariable final Long courseId,
                         @RequestHeader final Long userId) {
        log.info(String.format("Registration of user (%d) on course (%d)", userId, courseId));
    }
}
