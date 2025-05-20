package com.example.studentservice.service;

import com.example.studentservice.dto.StudentRequestDTO;
import com.example.studentservice.dto.StudentResponseDTO;
import com.example.studentservice.exception.EmailAlreadyExistsException;
import com.example.studentservice.exception.StudentNotFoundException;
import com.example.studentservice.mapper.StudentMapper;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDTO> getStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream().map(StudentMapper::toDTO).toList();
    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
       if(studentRepository.existsByEmail(studentRequestDTO.getEmail())) {
           throw new EmailAlreadyExistsException("A student with this email " + "already exists" + studentRequestDTO.getEmail());
       }

        Student newStudent = studentRepository.save(StudentMapper.toModel(studentRequestDTO));
        return StudentMapper.toDTO(newStudent);
    }

    public StudentResponseDTO getStudentById(UUID studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return StudentMapper.toDTO(student);
    }

    public StudentResponseDTO updateStudent(UUID id, StudentRequestDTO studentRequestDTO) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));

        if(studentRepository.existsByEmailAndIdNot(studentRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A student with this email " + "already exists" + studentRequestDTO.getEmail());
        }

        student.setName(studentRequestDTO.getName());
        student.setAddress(studentRequestDTO.getAddress());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPhone(studentRequestDTO.getPhone());
        student.setDateOfBirth(LocalDate.parse(studentRequestDTO.getDateOfBirth()));

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.toDTO(updatedStudent);
    }

    public void deleteStudent(UUID id){
        studentRepository.deleteById(id);
    }


}