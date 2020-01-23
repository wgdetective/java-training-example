package com.gpsolutions.edu.java.training.example.service;

    import com.gpsolutions.edu.java.training.example.dto.StudentSignUpRequest;
    import com.gpsolutions.edu.java.training.example.dto.UserSignInRequest;
    import org.springframework.stereotype.Service;

/**
 * @author Wladimir Litvinov
 */
@Service
public class StudentService {
    public String signUp(final StudentSignUpRequest request) {
        return "{\"id\":1}";
    }

    public String signIn(final UserSignInRequest request) {
        return "{\"id\":1}";
    }
}
