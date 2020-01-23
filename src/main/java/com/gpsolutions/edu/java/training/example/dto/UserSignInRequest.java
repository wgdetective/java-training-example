package com.gpsolutions.edu.java.training.example.dto;

import lombok.Data;

/**
 * @author Wladimir Litvinov
 */
@Data
public class UserSignInRequest {
    private String email;
    private String password;
}
