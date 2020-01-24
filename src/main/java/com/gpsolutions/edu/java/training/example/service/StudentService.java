package com.gpsolutions.edu.java.training.example.service;

import com.gpsolutions.edu.java.training.example.dto.StudentSignUpRequest;
import com.gpsolutions.edu.java.training.example.exception.SuchUserAlreadyExistException;
import com.gpsolutions.edu.java.training.example.security.LoadUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Wladimir Litvinov
 */
@Service
@AllArgsConstructor
public class StudentService {

    private final LoadUserDetailService loadUserDetailService;

    public void signUp(final StudentSignUpRequest request) throws SuchUserAlreadyExistException {
        try {
            if (loadUserDetailService.loadUserByUsername(request.getEmail()) != null) {
                throw new SuchUserAlreadyExistException("User with email=" + request.getEmail() + " already exists");
            }
        } catch (final UsernameNotFoundException e) {
            loadUserDetailService.saveUser(request.getEmail(), request.getPassword());
        }
    }
}
