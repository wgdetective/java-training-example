package com.gpsolutions.edu.java.training.example.service;

import com.gpsolutions.edu.java.training.example.dto.StudentSignUpRequest;
import com.gpsolutions.edu.java.training.example.dto.UserSignInRequest;
import com.gpsolutions.edu.java.training.example.excetion.SuchUserAlreadyExistException;
import com.gpsolutions.edu.java.training.example.security.LoadUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Wladimir Litvinov
 */
@Service
@AllArgsConstructor
public class StudentService {

    private final LoadUserDetailService loadUserDetailService;

    public void signUp(final StudentSignUpRequest request) throws SuchUserAlreadyExistException {
        if (loadUserDetailService.loadUserByUsername(request.getEmail()) != null) {
            throw new SuchUserAlreadyExistException();
        }
        loadUserDetailService.saveUser(request.getEmail(), request.getPassword());
    }

    public String signIn(final UserSignInRequest request) {
        return "{\"id\":1}";
    }
}
