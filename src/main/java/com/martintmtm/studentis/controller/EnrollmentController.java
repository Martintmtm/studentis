/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.controller;

import com.martintmtm.studentis.entity.Course;
import java.security.Principal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author martin
 */


@RestController
public class EnrollmentController {
    
    
    @GetMapping("user/enrollment")
    public String getAllEnrolledCourses (Principal principal) {
        return principal.getName();
    }
    
    
}
