package com.iwork.usermanagement.utility;

import com.iwork.usermanagement.exceptions.IworkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private Environment environment;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> generalExceptionHandler(Exception ex) {
        return new ResponseEntity<>(environment.getProperty("General.INTERNAL_SERVER_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IworkException.class)
    public ResponseEntity<String> customExceptionHandler(IworkException ex) {
        return new ResponseEntity<>(environment.getProperty(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
