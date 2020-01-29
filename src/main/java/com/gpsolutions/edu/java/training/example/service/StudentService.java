package com.gpsolutions.edu.java.training.example.service;

import com.gpsolutions.edu.java.training.example.dto.StudentSignUpRequest;
import com.gpsolutions.edu.java.training.example.entity.AuthInfoEntity;
import com.gpsolutions.edu.java.training.example.entity.UserEntity;
import com.gpsolutions.edu.java.training.example.exception.SuchUserAlreadyExistException;
import com.gpsolutions.edu.java.training.example.mapper.StudentSignUpRequestMapper;
import com.gpsolutions.edu.java.training.example.repository.AuthInfoRepository;
import com.gpsolutions.edu.java.training.example.repository.UserRepository;
import com.gpsolutions.edu.java.training.example.security.UserRole;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Wladimir Litvinov
 */
@Service
@AllArgsConstructor
public class StudentService {

    private final AuthInfoRepository authInfoRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private StudentSignUpRequestMapper studentSignUpRequestMapper;

    @Transactional
    public void signUp(final StudentSignUpRequest request) throws SuchUserAlreadyExistException {
        if (authInfoRepository.findByLogin(request.getEmail()).isPresent()) {
            throw new SuchUserAlreadyExistException("User with email=" + request.getEmail() + " already exists");
        }
        saveUser(request);
    }

    private void saveUser(final StudentSignUpRequest request) {
        final UserEntity userEntity = studentSignUpRequestMapper.sourceToDestination(request);
        userEntity.setUserRole(UserRole.STUDENT);
        final UserEntity savedUser = userRepository.save(userEntity);
        saveAuthInfo(request, savedUser);
    }

    private void saveAuthInfo(final StudentSignUpRequest request, final UserEntity savedUser) {
        final AuthInfoEntity authInfoEntity = new AuthInfoEntity();
        authInfoEntity.setLogin(request.getEmail());
        authInfoEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        authInfoEntity.setUser(savedUser);
        authInfoRepository.save(authInfoEntity);
    }
}
