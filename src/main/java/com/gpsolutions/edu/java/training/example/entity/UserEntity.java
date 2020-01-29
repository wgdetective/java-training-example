package com.gpsolutions.edu.java.training.example.entity;

import com.gpsolutions.edu.java.training.example.dto.Gender;
import com.gpsolutions.edu.java.training.example.security.UserRole;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 * @author Wladimir Litvinov
 */
@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String fio;
    private Gender gender;
    private LocalDate birthDate;
    private String selfDescription;

    private UserRole userRole;
}
