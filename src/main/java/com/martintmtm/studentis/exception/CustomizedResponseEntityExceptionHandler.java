/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author martin
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {
    
    @ExceptionHandler(CourseNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(CourseNotFoundException ex, WebRequest request) throws Exception {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(),
                ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(NotAuthorizedException.class)
    public final ResponseEntity<Object> handleUserNotAuthorizedException(NotAuthorizedException ex, WebRequest request) throws Exception {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(),
                ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(),
                ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
