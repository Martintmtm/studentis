/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.controller;

import com.martintmtm.studentis.entity.Course;
import com.martintmtm.studentis.entity.User;
import com.martintmtm.studentis.repository.CourseRepository;
import com.martintmtm.studentis.repository.UserRepository;
import java.net.URI;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author martin
 */


@RestController
public class EnrollmentController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @GetMapping("/user/enrollment")
    public Set<Course> getAllEnrolledCourses (Principal principal) {
        User user = getCurrentStudent(principal);
        return user.getEnrolledCourses();
    }
    
    @PostMapping("/user/enrollment")
    public Set<Course> enrollToClasses(@RequestBody List<Course> courses, Principal principal) {
        User user = getCurrentStudent(principal);
        
        courses.forEach(
            (course) -> user.getEnrolledCourses().add(new Course(course.getId()))
        );
        
        userRepository.save(user);
        return user.getEnrolledCourses();
    }
    
    @DeleteMapping("/user/enrollment")
    public User cancelEnrollmentToClasses(@RequestBody List<Course> courses, Principal principal) {
        User user = getCurrentStudent(principal);
        
        courses.forEach(
            (course) -> user.getEnrolledCourses().remove(new Course(course.getId()))
        );
        
        userRepository.save(user);
        return user;
    }
    
    private User getCurrentStudent(Principal principal) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(String.format("Username with name %s does not exist.", principal.getName()));  // throw unathorized request
        }
        return userOptional.get();
    }
    
    
    
    
}
