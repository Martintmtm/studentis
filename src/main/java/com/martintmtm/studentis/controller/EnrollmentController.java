/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.controller;

import com.martintmtm.studentis.entity.Course;
import com.martintmtm.studentis.entity.User;
import com.martintmtm.studentis.exception.CourseNotFoundException;
import com.martintmtm.studentis.exception.NotAuthorizedException;
import com.martintmtm.studentis.repository.CourseRepository;
import com.martintmtm.studentis.repository.UserRepository;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Set<Course> getAllEnrolledClasses (Principal principal) {
        User user = getCurrentStudent(principal);
        return user.getEnrolledCourses();
    }
    
    @PostMapping("/user/enrollment")
    public Set<Course> enrollToClasses(@RequestBody List<Course> courses, Principal principal) {
        User user = getCurrentStudent(principal);
        validateCoursesId(courses);
        
        courses.forEach(
            (course) -> user.getEnrolledCourses().add(new Course(course.getId()))
        );
        
        userRepository.save(user);
        return user.getEnrolledCourses();
    }
    
    @DeleteMapping("/user/enrollment")
    public void cancelEnrollmentFromClasses(@RequestBody List<Course> courses, Principal principal) {
        User user = getCurrentStudent(principal);
        validateCoursesId(courses);
        
        courses.forEach(
            (course) -> user.getEnrolledCourses().remove(new Course(course.getId()))
        );
        
        userRepository.save(user);
    }
    
    private User getCurrentStudent(Principal principal) {
        Optional<User> userOptional = userRepository.findByUsername(principal.getName());
        if (!userOptional.isPresent()) {
            throw new NotAuthorizedException("Not authorised.");  
        }
        return userOptional.get();
    }
    
    private void validateCoursesId(List<Course> courses) {
        for (Course course: courses) {
            Optional<Course> courseOptional = courseRepository.findById(course.getId());
             if(!courseOptional.isPresent()) {
                 throw new CourseNotFoundException(String.format("Not possible to enroll to entered classes. "
                         + "Reason: class with id %s does not exist.", course.getId()));                      
            }
        }
    }
    
    
    
    
}
