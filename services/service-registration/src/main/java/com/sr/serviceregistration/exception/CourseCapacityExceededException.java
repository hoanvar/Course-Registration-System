package com.sr.serviceregistration.exception;

public class CourseCapacityExceededException extends RuntimeException {
    public CourseCapacityExceededException(String message) {
        super(message);
    }
} 