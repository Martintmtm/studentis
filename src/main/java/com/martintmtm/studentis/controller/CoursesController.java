/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martintmtm.studentis.controller;

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
import com.martintmtm.studentis.repository.UserRepository;

/**
 *
 * @author martin
 */

@RestController
public class CoursesController {
    
    @Autowired
    private UserRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    // TODO let them know če je atribut ilegalen.
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
            throw new RuntimeException();       // TODO custom class
        }
        return course.get();
    }
    
}
