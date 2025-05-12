package com.sr.serviceregistration.service;

import com.sr.serviceregistration.dto.RegistrationRequestDTO;
import com.sr.serviceregistration.dto.RegistrationResponseDTO;
import com.sr.serviceregistration.exception.RegistrationNotFoundException;
import com.sr.serviceregistration.mapper.RegistrationMapper;
import com.sr.serviceregistration.model.Registration;
import com.sr.serviceregistration.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<RegistrationResponseDTO> getRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();

        List<RegistrationResponseDTO> registrationResponseDTOs = registrations.stream().map(RegistrationMapper::toDTO).toList();

        return registrationResponseDTOs;
    }


    public RegistrationResponseDTO createRegistration(RegistrationRequestDTO registrationRequestDTO) {
        Registration registration = registrationRepository.save(RegistrationMapper.toEntity(registrationRequestDTO));
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
