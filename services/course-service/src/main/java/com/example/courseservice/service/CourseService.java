package com.example.courseservice.service;

import com.example.courseservice.dto.CourseRequestDTO;
import com.example.courseservice.dto.CourseResponseDTO;
import com.example.courseservice.dto.RegistrationWithCourseDTO;
import com.example.courseservice.exception.CourseNotFoundException;
import com.example.courseservice.mapper.CourseMapper;
import com.example.courseservice.model.Course;
import com.example.courseservice.respository.CourseRespository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private static final Logger log = LoggerFactory.getLogger(CourseService.class);
    private CourseRespository courseRespository;

    private static final String COURSES_CACHE = "courses";

    public CourseService(CourseRespository courseRespository) {
        this.courseRespository = courseRespository;
    }

    @Cacheable(value = COURSES_CACHE, key = "'all_courses'", unless = "#result.isEmpty()")
    public List<CourseResponseDTO> getCourses() {
        log.info("Cache miss - Fetching courses from database");
        List<Course> courses = courseRespository.findAll();
        List<CourseResponseDTO> result = courses.stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Found {} courses in database", result.size());
        return result;
    }

    @CacheEvict(value = COURSES_CACHE, key = "'all_courses'")
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
        log.info("Creating new course and clearing all_courses cache");
        Course newCourse = courseRespository.save(CourseMapper.toModel(courseRequestDTO));
        return CourseMapper.toDTO(newCourse);
    }

    @Caching(evict = {
            @CacheEvict(value = COURSES_CACHE, key = "'all_courses'"),
            @CacheEvict(value = COURSES_CACHE, key = "#id")
    })
    public CourseResponseDTO updateCourse(UUID id, CourseRequestDTO courseRequestDTO) {
        log.info("Updating course with id: {} and clearing its cache and all_courses cache", id);
        Course course = courseRespository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course not found with ID" + id));
        course.setCourse_id(courseRequestDTO.getCourse_id());
        course.setCourse_name(courseRequestDTO.getCourse_name());
        course.setDescription(courseRequestDTO.getDescription());
        Course updatedCourse = courseRespository.save(course);
        return CourseMapper.toDTO(updatedCourse);
    }

    @Caching(evict = {
            @CacheEvict(value = COURSES_CACHE, key = "'all_courses'"),
            @CacheEvict(value = COURSES_CACHE, key = "#id")
    })
    public void deleteCourse(UUID id) {
        log.info("Deleting course with id: {} and clearing its cache and all_courses cache", id);
        courseRespository.deleteById(id);
    }

    @Cacheable(value = COURSES_CACHE, key = "#id", unless = "#result == null")
    public CourseResponseDTO getCourseById(UUID id) {
        log.info("Cache miss - Fetching course by id: {} from database", id);
        Course course = courseRespository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course not found with ID" + id));
        return CourseMapper.toDTO(course);
    }

    public List<CourseResponseDTO> getAllCourseWithId(List<UUID> ids) {
        List<Course> courses = courseRespository.findAllById(ids);
        List<CourseResponseDTO> result = courses.stream()
                .map(CourseMapper::toDTO)
                .toList();
        log.info("Found {} courses in database", result.size());
        return result;
    }

    // Cache delete
    @CacheEvict(value = COURSES_CACHE, allEntries = true)
    public void clearAllCache() {
        log.info("Clearing all courses cache");
    }
}
