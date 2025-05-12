package com.example.studentservice.exception;

public class EmailAlreadyExistsException extends RuntimeException
{
  public EmailAlreadyExistsException(String message) {
    super(message);
  }
}
