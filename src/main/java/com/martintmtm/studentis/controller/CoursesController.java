/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.controller;

import com.martintmtm.studentis.exception.CourseNotFoundException;
import com.martintmtm.studentis.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.martintmtm.studentis.repository.CourseRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author martin
 */

@RestController
public class CoursesController {
    
    @Autowired
    private CourseRepository courseRepository;
    
    @GetMapping("/classes")
    public List<Course> getAllClasses(@RequestParam(name = "name", required = false) String searchString) {
        if(searchString == null) {
            return courseRepository.findAll(); 
        } else {
            return courseRepository.findAll()
               .stream()
               .filter((course) -> course.getName().toUpperCase().contains(searchString.toUpperCase()))
               .collect(Collectors.toList());   
        }
    }
    
    @GetMapping("/classes/{id}")
    public Course getClassById(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);
        if(!course.isPresent()) {
            throw new CourseNotFoundException(String.format("Class with id %s does not exist", id));                      
        }
        return course.get();
    }
    
}
