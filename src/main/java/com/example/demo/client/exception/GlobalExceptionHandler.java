package com.example.demo.client.exception;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse clientNotFoundExceptionResponseEntity(ClientNotFoundException exception, WebRequest request) {
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
      errorResponse.setErrorMessage("No Client Data Avialble");
      errorResponse.setTimeStamp(LocalDateTime.now());
      return errorResponse;
    }

    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse FileUploadException(FileUploadException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorMessage("File should be PDF");
        errorResponse.setTimeStamp(LocalDateTime.now());
        return errorResponse;
    }

    @ExceptionHandler(ClientProjectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse ClientProjectNotFoundException(ClientProjectNotFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorMessage("Project Details or Clientdetail is null");
        errorResponse.setTimeStamp(LocalDateTime.now());
        return errorResponse;
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse ProjectNotFoundException(ProjectNotFoundException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorMessage("Project not found");
        errorResponse.setTimeStamp(LocalDateTime.now());
        return errorResponse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse ConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorMessage("Validation failed-Constraint Violation");
        errorResponse.setTimeStamp(LocalDateTime.now());
        return errorResponse;
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorMessage("SQL-Constraint Violation,Check Primary and ForeignKey values");
        errorResponse.setTimeStamp(LocalDateTime.now());
        return errorResponse;
    }

}
