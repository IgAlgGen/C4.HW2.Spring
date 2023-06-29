package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        // Возвращает статус 400 при возникновении Exception.
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler (JsonException.class)
    public ResponseEntity<?> employeeNotFoundException(IOException ioException) {
        // Возвращает статус 500 при возникновении Exception.
        return new ResponseEntity<>(ioException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
