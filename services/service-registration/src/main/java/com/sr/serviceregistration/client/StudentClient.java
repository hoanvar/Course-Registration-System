package com.sr.serviceregistration.client;

import com.sr.serviceregistration.dto.CourseResponseDTO;
import com.sr.serviceregistration.dto.StudentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "student-service", path = "/students")
public interface StudentClient {
    @GetMapping("/getStudentById/{id}")
    ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable UUID id);
} 