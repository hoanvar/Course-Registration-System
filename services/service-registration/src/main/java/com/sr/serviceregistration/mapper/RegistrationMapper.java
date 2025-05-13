package com.sr.serviceregistration.mapper;

import com.sr.serviceregistration.dto.RegistrationRequestDTO;
import com.sr.serviceregistration.dto.RegistrationResponseDTO;
import com.sr.serviceregistration.model.Registration;

public class RegistrationMapper {
    // cast object to data
    public static RegistrationResponseDTO toDTO(Registration registration) {
        RegistrationResponseDTO dto = new RegistrationResponseDTO();
        RegistrationResponseDTO registrationDTO = new RegistrationResponseDTO();
        registrationDTO.setId(registration.getId().toString());
        registrationDTO.setId_student(registration.getId_student());
        registrationDTO.setId_course(registration.getId_course());
        return registrationDTO;
    }


    // cast data to object
    public static Registration toEntity(RegistrationRequestDTO dto) {
        Registration registration = new Registration();
        registration.setId_course(dto.getId_course());
        registration.setId_student(dto.getId_student());
        return registration;
    }
}
