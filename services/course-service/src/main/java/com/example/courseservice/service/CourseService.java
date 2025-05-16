package com.example.courseservice.service;

import com.example.courseservice.dto.CourseRequestDTO;
import com.example.courseservice.dto.CourseResponseDTO;
import com.example.courseservice.exception.CourseNotFoundException;
import com.example.courseservice.mapper.CourseMapper;
import com.example.courseservice.model.Course;
import com.example.courseservice.respository.CourseRespository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private CourseRespository courseRespository;

    public CourseService(CourseRespository courseRespository) {
        this.courseRespository = courseRespository;
    }
    public List<CourseResponseDTO> getCourses(){
        List<Course> courses = courseRespository.findAll();

        List<CourseResponseDTO> courseDTOs = courses.stream()
                .map(CourseMapper::toDTO).toList();

        return courseDTOs;
    }

    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO){
        Course newCourse = courseRespository.save(CourseMapper.toModel(courseRequestDTO));
        return  CourseMapper.toDTO(newCourse);
    }

    public  CourseResponseDTO updateCourse(UUID id, CourseRequestDTO courseRequestDTO){
        Course course = courseRespository.findById(id).orElseThrow(
                ()-> new CourseNotFoundException("Course not found with ID" + id));
        course.setCourse_id(courseRequestDTO.getCourse_id());
        course.setCourse_name(courseRequestDTO.getCourse_name());
        course.setDescription(courseRequestDTO.getDescription());
        Course updatedCourse = courseRespository.save(course);
        return  CourseMapper.toDTO(updatedCourse);
    }

    public void  deleteCourse(UUID id){
        courseRespository.deleteById(id);
    }

}
