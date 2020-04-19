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
public class NotAuthorizedException  extends RuntimeException {
    
     public NotAuthorizedException(String message) {
        super(message);
    }
}
