package com.gpsolutions.edu.java.training.example.repository;

import com.gpsolutions.edu.java.training.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wladimir Litvinov
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
