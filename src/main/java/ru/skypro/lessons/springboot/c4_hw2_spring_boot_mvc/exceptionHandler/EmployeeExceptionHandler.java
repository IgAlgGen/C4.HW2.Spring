package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        // Возвращает статус 400 при возникновении Exception.
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
