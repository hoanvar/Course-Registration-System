package com.example.courseservice.respository;

import com.example.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRespository extends JpaRepository<Course, UUID> {
}
