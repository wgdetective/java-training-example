package com.gpsolutions.edu.java.training.example.repository;

import com.gpsolutions.edu.java.training.example.entity.AuthInfoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wladimir Litvinov
 */
@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfoEntity, Long> {

    Optional<AuthInfoEntity> findByLogin(String username);
}
