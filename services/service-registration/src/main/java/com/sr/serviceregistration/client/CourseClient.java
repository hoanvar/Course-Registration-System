package com.sr.serviceregistration.client;

import com.sr.serviceregistration.dto.CourseResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "course-service", path = "/course")
public interface CourseClient {
    @PostMapping("/getAllCourseWithId")
    ResponseEntity<List<CourseResponseDTO>> getAllCourseWithId(@RequestBody List<UUID> ids);

    @GetMapping("/getCourseById/{id}")
    ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable UUID id);
} 