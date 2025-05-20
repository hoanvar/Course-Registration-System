package com.sr.serviceregistration.service;

import com.sr.serviceregistration.client.StudentClient;
import com.sr.serviceregistration.dto.*;
import com.sr.serviceregistration.exception.RegistrationNotFoundException;
import com.sr.serviceregistration.mapper.RegistrationMapper;
import com.sr.serviceregistration.model.Registration;
import com.sr.serviceregistration.repository.RegistrationRepository;
import com.sr.serviceregistration.client.CourseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);
    private RegistrationRepository registrationRepository;
    private final CourseClient courseClient;
    private final StudentClient studentClient;

    public RegistrationService(RegistrationRepository registrationRepository, CourseClient courseClient, StudentClient studentClient) {
        this.registrationRepository = registrationRepository;
        this.courseClient = courseClient;
        this.studentClient = studentClient;
    }

    public List<RegistrationResponseDTO> getRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();

        List<RegistrationResponseDTO> registrationResponseDTOs = registrations.stream().map(RegistrationMapper::toDTO).toList();

        return registrationResponseDTOs;
    }

    public List<RegistrationWithCourseDTO> findAllByIdStudent(String id_student) {
        log.info("Finding registrations for student ID: {}", id_student);
        List<Registration> registrations = registrationRepository.findAllById_student(id_student);
        List<RegistrationResponseDTO> registrationDTOs = registrations.stream()
                .map(RegistrationMapper::toDTO)
                .toList();

        List<UUID> courseIds = registrationDTOs.stream()
                .map(reg -> UUID.fromString(reg.getId_course()))
                .toList();

        ResponseEntity<List<CourseResponseDTO>> courseResponse = courseClient.getAllCourseWithId(courseIds);
        List<CourseResponseDTO> courses = courseResponse.getBody() != null ? courseResponse.getBody() : List.of();

        return mergeRegistrationAndCourseDetails(registrationDTOs, courses);
    }

    private List<RegistrationWithCourseDTO> mergeRegistrationAndCourseDetails(
            List<RegistrationResponseDTO> registrations,
            List<CourseResponseDTO> courses) {
        
        return registrations.stream()
                .map(reg -> {
                    RegistrationWithCourseDTO dto = new RegistrationWithCourseDTO();
                    dto.setId(reg.getId());
                    dto.setId_student(reg.getId_student());
                    dto.setId_course(reg.getId_course());

                    courses.stream()
                            .filter(course -> course.getId().equals(reg.getId_course()))
                            .findFirst()
                            .ifPresent(course -> {
                                dto.setCourse_name(course.getCourse_name());
                                dto.setDescription(course.getDescription());
                            });

                    return dto;
                })
                .toList();
    }

    public RegistrationResponseDTO createRegistration(RegistrationRequestDTO registrationRequestDTO) {
        Registration registration = registrationRepository.save(RegistrationMapper.toEntity(registrationRequestDTO));
        CourseResponseDTO courseResponseDTO = courseClient.getCourseById(UUID.fromString(registration.getId_course())).getBody();
        StudentResponseDTO studentResponseDTO = studentClient.getStudentById(UUID.fromString(registration.getId_student())).getBody();

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setEmail(studentResponseDTO.getEmail());
        messageDTO.setStudent_name(studentResponseDTO.getName());
        messageDTO.setCourse_name(courseResponseDTO.getCourse_name());

        try {
            log.info("Sending notification message to Kafka for registration: {}", registration.getId());
            kafkaTemplate.send("notification", messageDTO)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Message sent successfully to topic: {}", result.getRecordMetadata().topic());
                    } else {
                        log.error("Failed to send message to Kafka", ex);
                    }
                });
        } catch (Exception e) {
            log.error("Error while sending message to Kafka", e);
        }

        return RegistrationMapper.toDTO(registration);
    }

    public RegistrationResponseDTO updateRegistration(UUID id, RegistrationRequestDTO registrationRequestDTO) {
        Registration registration = registrationRepository.findById(id).orElseThrow( () ->
                            new RegistrationNotFoundException("Registration not found with ID: "+ id));

        registration.setId_course(registrationRequestDTO.getId_course());
        registration.setId_student(registrationRequestDTO.getId_student());
        Registration registration1 = registrationRepository.save(registration);

        return RegistrationMapper.toDTO(registration1);
    }

    public RegistrationResponseDTO getRegistrationById (@PathVariable UUID id) {
        Registration registration = registrationRepository.findById(id).orElseThrow( () ->
                new RegistrationNotFoundException("Registration not found with ID: "+ id));
        return RegistrationMapper.toDTO(registration);
    }

    public void deleteRegistrationById(@PathVariable UUID id) {
        registrationRepository.deleteById(id);
    }
}
