package com.gpsolutions.edu.java.training.example.mapper;

import com.gpsolutions.edu.java.training.example.dto.Student;
import com.gpsolutions.edu.java.training.example.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * @author Wladimir Litvinov
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {

    UserEntity sourceToDestination(Student source);

    Student destinationToSource(UserEntity destination);
}
