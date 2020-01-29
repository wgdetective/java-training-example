package com.gpsolutions.edu.java.training.example.repository;

import com.gpsolutions.edu.java.training.example.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wladimir Litvinov
 */
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

}