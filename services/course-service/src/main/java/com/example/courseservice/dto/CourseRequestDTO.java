package com.example.courseservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseRequestDTO {
    @NotBlank(message = "Course ID is cannot be blank")
    private String course_id;

    @NotBlank(message = "Course name is cannot be blank")
    private String course_name;

    @NotBlank(message = "Desciption is cannot be blank")
    private String description;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

}
