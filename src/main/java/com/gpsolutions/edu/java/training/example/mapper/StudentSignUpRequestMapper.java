package com.gpsolutions.edu.java.training.example.mapper;

import com.gpsolutions.edu.java.training.example.dto.StudentSignUpRequest;
import com.gpsolutions.edu.java.training.example.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * @author Wladimir Litvinov
 */
@Mapper(componentModel = "spring")
public interface StudentSignUpRequestMapper {

    UserEntity sourceToDestination(StudentSignUpRequest source);

    StudentSignUpRequest destinationToSource(UserEntity destination);
}
