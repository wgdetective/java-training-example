package com.gpsolutions.edu.java.training.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Wladimir Litvinov
 */
@Data
public class Student {
    private Long id;
    private String email;
    private String fio;
    private Gender gender;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate birthDate;
    private String selfDescription;
}
