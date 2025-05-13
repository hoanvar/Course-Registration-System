package com.sr.serviceregistration.dto;

import jakarta.validation.constraints.NotBlank;

public class RegistrationRequestDTO {
    @NotBlank(message = "Student id can not be blank")
    private String id_student;

    @NotBlank(message = "Course id can not be blank")
    private String id_course;
    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public String getId_course() {
        return id_course;
    }

    public void setId_course(String id_course) {
        this.id_course = id_course;
    }


}
