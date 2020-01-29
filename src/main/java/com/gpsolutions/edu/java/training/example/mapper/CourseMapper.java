package com.gpsolutions.edu.java.training.example.mapper;

import com.gpsolutions.edu.java.training.example.dto.Course;
import com.gpsolutions.edu.java.training.example.entity.CourseEntity;
import org.mapstruct.Mapper;

/**
 * @author Wladimir Litvinov
 */
@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseEntity sourceToDestination(Course source);

    Course destinationToSource(CourseEntity destination);
}
