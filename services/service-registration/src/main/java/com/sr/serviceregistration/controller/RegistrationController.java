package com.sr.serviceregistration.controller;

import com.sr.serviceregistration.dto.RegistrationRequestDTO;
import com.sr.serviceregistration.dto.RegistrationResponseDTO;
import com.sr.serviceregistration.dto.RegistrationWithCourseDTO;
import com.sr.serviceregistration.model.Registration;
import com.sr.serviceregistration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
//import io.swagger.v3.annotation.tags;

import java.util.List;
import java.util.UUID;
//@Tag(name = "Registration", description = "API for managing Registrations")

@RestController
@RequestMapping("/registrations")
public class RegistrationController {


    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @GetMapping
    public ResponseEntity<List<RegistrationResponseDTO>> getAllRegistrations() {
        List<RegistrationResponseDTO> registrations = registrationService.getRegistrations();
        return ResponseEntity.ok().body(registrations);
    }


    @PostMapping
    public ResponseEntity<RegistrationResponseDTO> createRegistration(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        RegistrationResponseDTO registrationResponseDTO = registrationService.createRegistration(registrationRequestDTO);
        return ResponseEntity.ok().body(registrationResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationResponseDTO> updateRegistration(@PathVariable UUID id,
                                                                      @Valid @RequestBody RegistrationRequestDTO requestDTO) {
        RegistrationResponseDTO registrationResponseDTO = registrationService.updateRegistration(id, requestDTO);
        return ResponseEntity.ok().body(registrationResponseDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RegistrationResponseDTO> getRegistrationById(@PathVariable UUID id) {
        RegistrationResponseDTO registration = registrationService.getRegistrationById(id);
        return ResponseEntity.ok().body(registration);
    }


    @GetMapping("/student/{id_student}")
    public ResponseEntity<List<RegistrationWithCourseDTO>> getRegistrationByStudentId(@PathVariable String id_student) {
        List<RegistrationWithCourseDTO> registrations = registrationService.findAllByIdStudent(id_student);

        return ResponseEntity.ok().body(registrations);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistrationById(@PathVariable UUID id) {
        registrationService.deleteRegistrationById(id);
        return ResponseEntity.noContent().build();
    }

}
