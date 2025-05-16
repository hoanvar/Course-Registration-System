package com.example.courseservice.mapper;

import com.example.courseservice.dto.CourseRequestDTO;
import com.example.courseservice.dto.CourseResponseDTO;
import com.example.courseservice.model.Course;

public class CourseMapper {
    public static CourseResponseDTO toDTO(Course course){
        CourseResponseDTO courseDTO = new CourseResponseDTO();
        courseDTO.setId(course.getId().toString());
        courseDTO.setCourse_id(course.getCourse_id().toString());
        courseDTO.setCourse_name(course.getCourse_name());
        courseDTO.setDescription(course.getDescription());
        return courseDTO;
    }

    public static Course toModel(CourseRequestDTO courseDTO){
        Course course = new Course();
        course.setCourse_id(courseDTO.getCourse_id());
        course.setCourse_name(courseDTO.getCourse_name());
        course.setDescription(courseDTO.getDescription());
        return course;
    }
}
