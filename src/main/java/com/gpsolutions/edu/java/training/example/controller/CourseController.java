package com.gpsolutions.edu.java.training.example.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wladimir Litvinov
 */
@RestController
@RequestMapping("/course")
@Log
public class CourseController {
    @GetMapping(value = "/list")
    public String getList() {
        return "[\n" +
               "  {\n" +
               "    \"id\" : 1, \n" +
               "    \"title\" : \"GP Java Training Winter 2019-2020\",\n" +
               "    \"description\" : \"Курс по обучению старту проектов на языке Java\",\n" +
               "    \"startDate\" : \"04.02.2020\", \n" +
               "    \"endDate\" : \"28.02.2020\",\n" +
               "    \"teacherName\" : \"Литвинов Владимир Дмитриевич\" \n" +
               "  }\n" +
               "]";
    }
}
