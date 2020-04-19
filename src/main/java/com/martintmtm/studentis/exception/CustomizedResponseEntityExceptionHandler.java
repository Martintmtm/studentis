/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.exception;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author martin
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);
    
    @ExceptionHandler(CourseNotFoundException.class)
    public final ResponseEntity<Object> courseNotFoundException(CourseNotFoundException ex, WebRequest request) throws Exception {
        CustomeErrorResponse exceptionResponse = new CustomeErrorResponse(new Date(),
                ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public final ResponseEntity<Object> handleUserNotAuthorizedException(NotAuthorizedException ex, WebRequest request) throws Exception {
        CustomeErrorResponse exceptionResponse = new CustomeErrorResponse(new Date(),
                ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
    }
    
    // handles @Validated on controller level
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintValidationException(ConstraintViolationException ex, WebRequest request) throws Exception {
        CustomeErrorResponse exceptionResponse = new CustomeErrorResponse(new Date(), ex.getMessage());       
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        CustomeErrorResponse exceptionResponse = new CustomeErrorResponse(new Date(), "internal server error: ");
        LOGGER.error("error:", ex);        // logging internal server error. Do not expose api to the end user. 
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
