package com.example.studentservice.mapper;

import com.example.studentservice.dto.StudentRequestDTO;
import com.example.studentservice.dto.StudentResponseDTO;
import com.example.studentservice.model.Student;

import java.time.LocalDate;

public class StudentMapper {
    public static StudentResponseDTO toDTO(Student student) {
        StudentResponseDTO studentDTO = new StudentResponseDTO();
        studentDTO.setId(student.getId().toString());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setDateOfBirth(student.getDateOfBirth().toString());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setPhone(student.getPhone());
        return studentDTO;
    }
    public static Student toModel(StudentRequestDTO studentRequestDTO) {
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setDateOfBirth(LocalDate.parse(studentRequestDTO.getDateOfBirth()));
        student.setAddress(studentRequestDTO.getAddress());
        student.setPhone(studentRequestDTO.getPhone());
        return student;
    }
}
