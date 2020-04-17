/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.exception;

/**
 *
 * @author martin
 */
public class CourseNotFoundException extends RuntimeException{
    
     public CourseNotFoundException(String message) {
        super(message);
    }
}
