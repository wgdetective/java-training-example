package com.gpsolutions.edu.java.training.example.controller;

import com.gpsolutions.edu.java.training.example.dto.StudentSignUpRequest;
import com.gpsolutions.edu.java.training.example.dto.UserSignInRequest;
import com.gpsolutions.edu.java.training.example.dto.UserSignInResponse;
import com.gpsolutions.edu.java.training.example.excetion.SuchUserAlreadyExistException;
import com.gpsolutions.edu.java.training.example.security.JwtUtil;
import com.gpsolutions.edu.java.training.example.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wladimir Litvinov
 */
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final StudentService studentService;

    @PostMapping(value = "/student/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponse singUp(@RequestBody final StudentSignUpRequest request)
        throws SuchUserAlreadyExistException {
        studentService.signUp(request);
        return singIn(new UserSignInRequest(request.getEmail(), request.getPassword()));
    }

    @PostMapping(value = "/student/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSignInResponse singIn(@RequestBody final UserSignInRequest request) {
        authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        return new UserSignInResponse(
            jwtUtil.generateToken(
                new User(request.getEmail(), request.getPassword(), List.of(new SimpleGrantedAuthority("STUDENT")))));
    }
}
