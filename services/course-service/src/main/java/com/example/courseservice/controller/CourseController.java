package com.example.courseservice.controller;

import com.example.courseservice.dto.CourseRequestDTO;
import com.example.courseservice.dto.CourseResponseDTO;
import com.example.courseservice.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getCourses(){
        List<CourseResponseDTO> courseDTOs = courseService.getCourses();
        return ResponseEntity.ok(courseDTOs);
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(
            @Valid @RequestBody CourseRequestDTO courseRequestDTO){
        CourseResponseDTO courseResponseDTO = courseService.createCourse(
                courseRequestDTO);

        return ResponseEntity.ok().body(courseResponseDTO);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable UUID id,
                                                           @Validated({Default.class}) @RequestBody CourseRequestDTO courseRequestDTO){
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(id, courseRequestDTO);
        return  ResponseEntity.ok().body(courseResponseDTO);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteCourse(@PathVariable UUID id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
