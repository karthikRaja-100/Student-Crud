package com.CRUD_Application.demo.exceptionhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class exception {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDataType(HttpMessageNotReadableException ex) {
        Map<String, String> response = new HashMap<>();

        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            String fieldName = invalidFormatException.getPath().get(0).getFieldName();
            response.put("status", "error");

            if ("age".equals(fieldName)) {
                response.put("message", "Invalid input for 'age'. Please enter a valid number.");
            } else if ("salary".equals(fieldName)) {
                response.put("message", "Invalid input for 'salary'. Please enter a valid  number.");
            } else {
                response.put("message", "Invalid input format. Please check your request.");
            }
        } else {
            response.put("status", "error");
            response.put("message", "Invalid input format. Please check your request.");
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
