/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.exception;

import java.util.Date;

/**
 *
 * @author martin
 */
public class CustomExceptionResponse {
    private Date timestamp;
    private String message;

    public CustomExceptionResponse() {
    }

    public CustomExceptionResponse(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
    

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CustomExceptionResponse{" + "timestamp=" + timestamp + ", message=" + message + '}';
    }
    
    
    
}
